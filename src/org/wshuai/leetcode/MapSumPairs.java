package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/08/2019.
 * #0677 https://leetcode.com/problems/map-sum-pairs/
 */
public class MapSumPairs {

	private TrieNode root;
	private Map<String, Integer> map;

	/**
	 * Initialize your data structure here.
	 */
	public MapSumPairs() {
		root = new TrieNode();
		map = new HashMap<>();
	}

	public void insert(String key, int val) {
		int diff = val;
		if (map.containsKey(key)) {
			diff = val - map.get(key);
		}
		map.put(key, val);
		TrieNode node = root;
		for (char c : key.toCharArray()) {
			if (!node.containsKey(c)) {
				node.put(c, new TrieNode());
			}
			node = node.get(c);
			node.addSum(diff);
		}
		node.setEnd();
	}

	public int sum(String prefix) {
		TrieNode node = root;
		for (char c : prefix.toCharArray()) {
			if (!node.containsKey(c)) {
				return 0;
			}
			node = node.get(c);
		}
		return node.getSum();
	}

	private class TrieNode {

		private static final int R = 26;

		private TrieNode[] links;

		private boolean isEnd;

		private int sum;

		public TrieNode() {
			links = new TrieNode[R];
			sum = 0;
		}

		public boolean containsKey(char key) {
			return links[key - 'a'] != null;
		}

		public TrieNode get(char key) {
			return links[key - 'a'];
		}

		public void put(char key, TrieNode node) {
			links[key - 'a'] = node;
		}

		public boolean isEnd() {
			return isEnd;
		}

		public void setEnd() {
			isEnd = true;
		}

		public int getSum() {
			return sum;
		}

		public void addSum(int val) {
			sum += val;
		}
	}
}
