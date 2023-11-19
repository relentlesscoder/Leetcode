package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/26/2023.
 * #2352 https://leetcode.com/problems/equal-row-and-column-pairs/
 */
public class EqualRowAndColumnPairs {

	// time O(n^2), space O(n^2)
	public int equalPairsTrie(int[][] grid) {
		int n = grid.length;
		TrieNode root = new TrieNode();
		for (int r = 0; r < n; r++) {
			TrieNode curr = root;
			for (int c = 0; c < n; c++) {
				if (!curr.containsKey(grid[r][c])) {
					curr.put(grid[r][c], new TrieNode());
				}
				curr = curr.get(grid[r][c]);
			}
			curr.setEnd();
		}
		int res = 0;
		for (int c = 0; c < n; c++) {
			TrieNode curr = root;
			for (int r = 0; r < n; r++) {
				if (!curr.containsKey(grid[r][c])) {
					break;
				}
				curr = curr.get(grid[r][c]);
			}
			if (curr.isEnd()) {
				res += curr.getCount();
			}
		}
		return res;
	}

	// time O(n^2), space O(n^2)
	public int equalPairsHashmap(int[][] grid) {
		int res = 0, n = grid.length;
		Map<String, Integer> rowCount = new HashMap<>();
		for (int[] row : grid) {
			String key = Arrays.toString(row);
			rowCount.put(key, rowCount.getOrDefault(key, 0) + 1);
		}
		for (int i = 0; i < n; i++) {
			int[] col = new int[n];
			for (int j = 0; j < n; j++) {
				col[j] = grid[j][i];
			}
			res += rowCount.getOrDefault(Arrays.toString(col), 0);
		}
		return res;
	}

	private class TrieNode {

		private int count;
		private boolean isEnd;
		private Map<Integer, TrieNode> nodes;

		public TrieNode() {
			count = 0;
			isEnd = false;
			this.nodes = new HashMap<>();
		}

		public TrieNode get(int key) {
			return nodes.get(key);
		}

		public boolean containsKey(int key) {
			return nodes.containsKey(key);
		}

		public void put(int key, TrieNode node) {
			nodes.put(key, node);
		}

		public boolean isEnd() {
			return isEnd;
		}

		public int getCount() {
			return count;
		}

		public void setEnd() {
			count++;
			isEnd = true;
		}

	}
}
