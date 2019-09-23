package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 9/8/2019.
 * #677 https://leetcode.com/problems/map-sum-pairs/
 */
public class MapSumPairs {
	private Map<String, Integer> map;
	private ImplementTrie trie;
	private int sum;

	/**
	 * Initialize your data structure here.
	 */
	public MapSumPairs() {
		map = new HashMap<>();
		trie = new ImplementTrie();
	}

	public void insert(String key, int val) {
		map.put(key, val);
		trie.insert(key);
	}

	public int sum(String prefix) {
		sum = 0;
		TrieNode node = trie.searchPrefix(prefix);
		dfs(node, prefix);
		return sum;
	}

	// DFS + Trie
	private void dfs(TrieNode node, String curr) {
		if (node == null) {
			return;
		}
		if (node.isEnd()) {
			sum += map.getOrDefault(curr, 0);
		}
		for (int i = 0; i < 26; i++) {
			char key = (char) ('a' + i);
			if (node.containsKey(key)) {
				dfs(node.get(key), curr + key);
			}
		}
	}
}
