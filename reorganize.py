#!/usr/bin/env python3
"""Reorganize LeetCode Java solutions by topic using LeetCode's GraphQL API.

Usage:
    python3 reorganize.py             # Full reorganization
    python3 reorganize.py --dry-run   # Preview categories without moving files
    python3 reorganize.py --refresh   # Re-fetch topic data from LeetCode API
"""

import os
import re
import json
import sys
import time
import shutil
from pathlib import Path
from collections import Counter

try:
    import requests
except ImportError:
    print("Error: 'requests' library not found. Install it with:")
    print("  pip3 install requests")
    sys.exit(1)

SCRIPT_DIR = Path(__file__).parent
LEETCODE_SRC = SCRIPT_DIR / "src" / "org" / "wshuai" / "leetcode"
CACHE_FILE = SCRIPT_DIR / ".lc_topics_cache.json"

# Topic tag priority — checked in order, first matching group wins.
# A problem is assigned the category of the first group whose tags intersect
# with that problem's LeetCode topic tags.
PRIORITY_TAGS = [
    ({"dynamic-programming", "memoization"}, "dp"),
    ({"trie", "segment-tree", "binary-indexed-tree",
      "binary-search-tree", "binary-tree", "tree"}, "tree"),
    ({"backtracking"}, "backtracking"),
    ({"divide-and-conquer"}, "divideandconquer"),
    ({"graph", "topological-sort", "shortest-path", "minimum-spanning-tree",
      "union-find", "biconnected-component", "strongly-connected-component",
      "eulerian-circuit"}, "graph"),
    ({"heap-priority-queue"}, "heap"),
    ({"greedy"}, "greedy"),
    ({"binary-search"}, "binarysearch"),
    ({"linked-list", "doubly-linked-list"}, "linkedlist"),
    ({"stack", "queue", "monotonic-stack", "monotonic-queue"}, "stackqueue"),
    ({"string", "string-matching", "suffix-array", "rolling-hash"}, "string"),
    ({"hash-table", "hash-function"}, "hash"),
    ({"design", "iterator", "data-stream", "concurrency", "ordered-set"}, "design"),
    # DFS/BFS as a fallback — fires only when no more-specific tag matched above
    ({"depth-first-search", "breadth-first-search"}, "graph"),
    ({"sorting", "counting-sort", "bucket-sort", "merge-sort", "radix-sort"}, "sorting"),
    ({"simulation"}, "simulation"),
    ({"two-pointers", "sliding-window", "prefix-sum", "matrix", "array"}, "array"),
    ({"math", "bit-manipulation", "number-theory", "combinatorics", "enumeration",
      "counting", "probability-and-statistics", "game-theory", "geometry",
      "brainteaser", "randomized", "line-sweep", "reservoir-sampling",
      "rejection-sampling"}, "math"),
]

CATEGORY_DISPLAY = {
    "dp":             "Dynamic Programming",
    "tree":           "Tree / Trie / Segment Tree",
    "backtracking":   "Backtracking",
    "divideandconquer": "Divide and Conquer",
    "graph":          "Graph / BFS / DFS / Union Find",
    "heap":           "Heap / Priority Queue",
    "greedy":         "Greedy",
    "binarysearch":   "Binary Search",
    "linkedlist":     "Linked List",
    "stackqueue":     "Stack / Queue",
    "string":         "String",
    "hash":           "Hash Table",
    "design":         "Design / Data Structure",
    "sorting":        "Sorting",
    "simulation":     "Simulation",
    "array":          "Array / Matrix / Two Pointers",
    "math":           "Math / Bit Manipulation",
    "other":          "Other (no URL or unknown slug)",
}


# ── API ───────────────────────────────────────────────────────────────────────

def fetch_tags_for_slug(slug: str, session: requests.Session) -> list:
    """Fetch topic tag slugs for a single LeetCode problem slug."""
    url = "https://leetcode.com/graphql"
    query = """
    query getQuestionDetail($titleSlug: String!) {
      question(titleSlug: $titleSlug) {
        topicTags { slug }
      }
    }
    """
    try:
        resp = session.post(
            url,
            json={"query": query, "variables": {"titleSlug": slug}},
            timeout=15,
        )
        resp.raise_for_status()
        data = resp.json().get("data", {}).get("question")
        if data:
            return [t["slug"] for t in data["topicTags"]]
    except Exception:
        pass
    return []


def fetch_tags_for_slugs(slugs: list) -> dict:
    """Fetch topic tags for all given slugs using a thread pool."""
    from concurrent.futures import ThreadPoolExecutor, as_completed
    import threading

    slug_to_tags: dict = {}
    lock = threading.Lock()
    completed = [0]
    total = len(slugs)
    WORKERS = 8

    session = requests.Session()
    session.headers.update({
        "Content-Type": "application/json",
        "Referer": "https://leetcode.com/",
    })

    def fetch_one(slug):
        tags = fetch_tags_for_slug(slug, session)
        with lock:
            slug_to_tags[slug] = tags
            completed[0] += 1
            if completed[0] % 100 == 0 or completed[0] == total:
                print(f"  Fetched {completed[0]}/{total}...", flush=True)
        time.sleep(0.05)  # gentle rate limit per thread
        return slug, tags

    print(f"  Querying LeetCode for {total} problem slugs ({WORKERS} threads)...")
    with ThreadPoolExecutor(max_workers=WORKERS) as executor:
        futures = {executor.submit(fetch_one, s): s for s in slugs}
        for f in as_completed(futures):
            f.result()  # propagate exceptions if any

    return slug_to_tags


# ── Helpers ───────────────────────────────────────────────────────────────────

def determine_category(tags: list) -> str:
    tag_set = set(tags)
    for tag_group, category in PRIORITY_TAGS:
        if tag_group & tag_set:
            return category
    return "other"


def get_slug_from_file(filepath: Path):
    with open(filepath, encoding="utf-8", errors="ignore") as f:
        header = f.read(600)
    m = re.search(r"leetcode\.com/problems/([a-z0-9-]+)", header)
    return m.group(1) if m else None


# ── Main ──────────────────────────────────────────────────────────────────────

def main():
    dry_run = "--dry-run" in sys.argv
    refresh  = "--refresh"  in sys.argv

    if dry_run:
        print("=== DRY RUN — no files will be moved ===\n")

    # 1. Collect flat .java files and extract slugs
    java_files = sorted(LEETCODE_SRC.glob("*.java"))
    print(f"Found {len(java_files)} flat Java files to categorize.\n")

    file_slug: dict = {}
    no_url: list[str] = []
    for jf in java_files:
        slug = get_slug_from_file(jf)
        if slug:
            file_slug[jf] = slug
        else:
            no_url.append(jf.name)

    # 2. Load cache or fetch from LeetCode
    if CACHE_FILE.exists() and not refresh:
        with open(CACHE_FILE) as f:
            slug_to_tags = json.load(f)
        print(f"Loaded {len(slug_to_tags)} entries from cache ({CACHE_FILE.name}).")
    else:
        slug_to_tags = {}

    # Find slugs not yet cached
    needed = [s for s in set(file_slug.values()) if s not in slug_to_tags]
    if needed:
        print(f"Fetching topic data from LeetCode API for {len(needed)} new slugs...")
        new_data = fetch_tags_for_slugs(needed)
        slug_to_tags.update(new_data)
        with open(CACHE_FILE, "w") as f:
            json.dump(slug_to_tags, f)
        print(f"Cache updated → {len(slug_to_tags)} total entries in {CACHE_FILE.name}\n")
    else:
        print("All slugs found in cache.\n")

    # 3. Categorize
    file_category: dict = {}
    no_match: list = []

    for jf in java_files:
        if jf not in file_slug:
            file_category[jf] = "other"
            continue
        slug = file_slug[jf]
        tags = slug_to_tags.get(slug, [])
        if not tags:
            no_match.append((jf.name, slug))
        file_category[jf] = determine_category(tags)

    # 4. Print distribution
    dist = Counter(file_category.values())
    print("Category distribution:")
    all_cats = [c for _, c in PRIORITY_TAGS] + ["other"]
    seen = set()
    for cat in all_cats:
        if cat in dist and cat not in seen:
            seen.add(cat)
            label = CATEGORY_DISPLAY.get(cat, cat)
            print(f"  {label:40s}: {dist[cat]:4d} files  →  {cat}/")
    print()

    if no_url:
        print(f"  {len(no_url)} files had no LeetCode URL → 'other/'")
    if no_match:
        print(f"  {len(no_match)} slugs not found in API data → 'other/'")
        for name, slug in no_match[:10]:
            print(f"    {name}  (slug: {slug})")
        if len(no_match) > 10:
            print(f"    ... and {len(no_match) - 10} more")

    if dry_run:
        print("\nDry run complete. Re-run without --dry-run to apply changes.")
        return

    # 5. Move files and update package declarations
    print("\nMoving files...")
    moved, errors = 0, 0

    for jf, cat in file_category.items():
        dest_dir = LEETCODE_SRC / cat
        dest_dir.mkdir(exist_ok=True)
        dest = dest_dir / jf.name
        new_pkg = f"org.wshuai.leetcode.{cat}"

        try:
            content = jf.read_text(encoding="utf-8", errors="ignore")
            content = re.sub(
                r"^package org\.wshuai\.leetcode;",
                f"package {new_pkg};",
                content,
                count=1,
                flags=re.MULTILINE,
            )
            dest.write_text(content, encoding="utf-8")
            jf.unlink()
            moved += 1
        except Exception as e:
            print(f"  ERROR {jf.name}: {e}")
            errors += 1

    print(f"\nDone! Moved {moved} files. Errors: {errors}.")
    print("\nNext steps:")
    print("  1. In IntelliJ: File > Invalidate Caches / Restart")
    print("  2. Re-index the project and verify there are no compilation errors.")


if __name__ == "__main__":
    main()
