package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 01/22/2024.
 * #2781 https://leetcode.com/problems/length-of-the-longest-valid-substring/
 */
public class LengthOfTheLongestValidSubstring {

    // O((m + n) * l), space O(m * l)
    public int longestValidSubstringSlidingWindowWithTrie(String word, List<String> forbidden) {
        // 1. Max length of forbidden word is 10.
        // 2. If a forbidden word is found at index l then the left end of
        //    the substring can't go back before l anytime in future when we
        //    extend the right end so sliding window can be used.
        // 3. To find the longest valid substring, we can use a Trie to match
        //    from right end of the window to left and break immediately once
        //    we found a match (which is the shortest forbidden word).
        TrieNode root = new TrieNode();
        for (String f : forbidden) { // O(m)
            insert(f, root); // O(l)
        }
        int res = 0, n = word.length();
        for (int right = 0, left = 0; right < n; right++) { // O(n)
            TrieNode curr = root;
            for (int i = right; i >= left && i > right - 10; i--) { // O(l)
                char c = word.charAt(i);
                if (!curr.containsKey(c)) {
                    break;
                }
                curr = curr.get(c);
                if (curr.isEnd()) {
                    left = i + 1; // Advance the left end once we found a match
                    break;
                }
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    private void insert(String word, TrieNode root) {
        TrieNode curr = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            if (!curr.containsKey(c)) {
                curr.put(c, new TrieNode());
            }
            curr = curr.get(c);
        }
        curr.setEnd();
    }

    private static class TrieNode {

        private static final int SIZE = 26;
        private boolean end;
        private final TrieNode[] nodes;

        public TrieNode() {
            nodes = new TrieNode[SIZE];
            end = false;
        }

        public boolean containsKey(char c) {
            return nodes[c - 'a'] != null;
        }

        public void put(char c, TrieNode node) {
            nodes[c - 'a'] = node;
        }

        public TrieNode get(char c) {
            return nodes[c - 'a'];
        }

        public boolean isEnd() {
            return this.end;
        }

        public void setEnd() {
            this.end = true;
        }
    }

    // time O(n * l^2 + m * l), space O(m)
    public int longestValidSubstringSlidingWindowWithHashSet(String word, List<String> forbidden) {
        // Initialize hashset for m strings with max length l takes O(m * l)
        Set<String> set = new HashSet<>(forbidden); // O(m * l)
        int res = 0, n = word.length();
        for (int right = 0, left = 0; right < n; right++) { // O(n)
            for (int i = right; i >= left && i > right - 10; i--) { // O(l)
                if (set.contains(word.substring(i, right + 1))) { // O(l)
                    left = i + 1;
                    break;
                }
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
