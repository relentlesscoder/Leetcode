package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/14/2019.
 * #0737 https://leetcode.com/problems/sentence-similarity-ii/
 */
public class SentenceSimilarityII {

	private Map<String, String> roots = new HashMap<>();
	private Map<String, Integer> size = new HashMap<>();

	// time O((m + n) * α(n) * max_len), space O(m * max_len)
	public boolean areSentencesSimilarTwoUnionFind(String[] sentence1, String[] sentence2,
										  List<List<String>> similarPairs) {
		if (sentence1.length != sentence2.length) {
			return false;
		}
		for (List<String> edge : similarPairs) {
			roots.computeIfAbsent(edge.get(0), value -> edge.get(0));
			roots.computeIfAbsent(edge.get(1), value -> edge.get(1));
			size.computeIfAbsent(edge.get(0), value -> 1);
			size.computeIfAbsent(edge.get(1), value -> 1);
			union(edge.get(0), edge.get(1));
		}
		for (int i = 0; i < sentence1.length; i++) {
			if (sentence1[i].equals(sentence2[i])) {
				continue;
			}
			if (!roots.containsKey(sentence1[i]) || !roots.containsKey(sentence2[i])) {
				return false;
			}
			if (!find(sentence1[i]).equals(find(sentence2[i]))) {
				return false;
			}
		}
		return true;
	}

	private void union(String word1, String word2) {
		String root1 = find(word1), root2 = find(word2);
		if (root1.equals(root2)) {
			return;
		}
		if (size.get(root1) > size.get(root2)) {
			roots.put(root2, root1);
			size.put(root1, size.get(root1) + size.get(root2));
		} else {
			roots.put(root1, root2);
			size.put(root2, size.get(root2) + size.get(root1));
		}
	}

	private String find(String node) {
		if (!node.equals(roots.get(node))) {
			String root = find(roots.get(node));
			roots.put(node, root);
		}
		return roots.get(node);
	}

	// time O(n * m * max_len), space O(m * max_len)
	public boolean areSentencesSimilarTwoDFS(String[] sentence1, String[] sentence2,
											 List<List<String>> similarPairs) {
		if (sentence1.length != sentence2.length) {
			return false;
		}
		Map<String, Set<String>> adj = new HashMap<>();
		for (List<String> edge : similarPairs) {
			adj.computeIfAbsent(edge.get(0), value -> new HashSet<>()).add(edge.get(1));
			adj.computeIfAbsent(edge.get(1), value -> new HashSet<>()).add(edge.get(0));
		}
		for (int i = 0; i < sentence1.length; i++) {
			if (sentence1[i].equals(sentence2[i])) {
				continue;
			}
			Set<String> visited = new HashSet<>();
			if (!dfs(sentence1[i], sentence2[i], visited, adj)) {
				return false;
			}
		}
		return true;
	}

	private boolean dfs(String word, String target, Set<String> visited, Map<String, Set<String>> adj) {
		visited.add(word);
		if (word.equals(target)) {
			return true;
		}
		if (!adj.containsKey(word)) {
			return false;
		}
		for (String next : adj.get(word)) {
			if (!visited.contains(next) && dfs(next, target, visited, adj)) {
				return true;
			}
		}
		return false;
	}

	// time O(n * m * max_len), space O(m * max_len)
	public boolean areSentencesSimilarTwoBFS(String[] sentence1, String[] sentence2,
											 List<List<String>> similarPairs) {
		if (sentence1.length != sentence2.length) {
			return false;
		}
		Map<String, Set<String>> adj = new HashMap<>();
		for (List<String> edge : similarPairs) {
			adj.computeIfAbsent(edge.get(0), value -> new HashSet<>()).add(edge.get(1));
			adj.computeIfAbsent(edge.get(1), value -> new HashSet<>()).add(edge.get(0));
		}
		for (int i = 0; i < sentence1.length; i++) {
			if (sentence1[i].equals(sentence2[i])) {
				continue;
			}
			if (!adj.containsKey(sentence1[i]) || !adj.containsKey(sentence2[i])) {
				return false;
			}
			if (!bfs(sentence1[i], sentence2[i], adj)) {
				return false;
			}
		}
		return true;
	}

	private boolean bfs(String source, String target, Map<String, Set<String>> adj) {
		Set<String> visited = new HashSet<>();
		Deque<String> queue = new ArrayDeque<>();
		queue.offer(source);
		visited.add(source);
		while (!queue.isEmpty()) {
			String curr = queue.poll();
			if (!adj.containsKey(curr)) {
				continue;
			}
			for (String next : adj.get(curr)) {
				if (next.equals(target)) {
					return true;
				}
				if (!visited.contains(next)) {
					visited.add(next);
					queue.offer(next);
				}
			}
		}
		return false;
	}
}
