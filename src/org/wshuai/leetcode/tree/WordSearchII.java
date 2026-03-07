package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 02/14/2017.
 * #0212 https://leetcode.com/problems/word-search-ii/
 */
public class WordSearchII {

	private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};

	// time O(m * n * 3^L), space O(k * L)
	public List<String> findWords(char[][] board, String[] words) {
		// 用类似 #0079 的方法在网格中搜索目标单词，由于一个一个单词搜索会超时。我们
		// 可以将所有的单词都存在字典树中，在搜索时同时搜索字典树。
		Set<String> res = new HashSet<>();
		TrieNode root = new TrieNode();
		for (String s : words) { // O(k)
			insert(s, root); // O(L)
		}
		int m = board.length, n = board[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (root.containsKey(board[i][j])) {
					dfs(i, j, new StringBuilder(), root, board, res);
				}
			}
		}
		return new ArrayList<>(res);
	}

	private void dfs(int i, int j, StringBuilder sb, TrieNode node, char[][] grid, Set<String> res) {
		char c = grid[i][j];
		int len = sb.length();
		sb.append(c);
		TrieNode next = node.get(c);
		if (next.isEnd()) {
			res.add(sb.toString());
		}
		grid[i][j] = '#';
		for (int d = 0; d < 4; d++) {
			int x = i + DIRS[d], y = j + DIRS[d + 1];
			if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length
					&& grid[x][y] != '#' && next.containsKey(grid[x][y])) {
				dfs(x, y, sb, next, grid, res);
			}
		}
		// 递归完恢复现场
		grid[i][j] = c;
		sb.setLength(len);
	}

	private void insert(String s, TrieNode root) {
		TrieNode curr = root;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!curr.containsKey(c)) {
				curr.put(c, new TrieNode());
			}
			curr = curr.get(c);
		}
		curr.setEnd();
	}

	private static class TrieNode {

		private final TrieNode[] nodes;
		private boolean isEnd;

		public TrieNode() {
			nodes = new TrieNode[26];
			isEnd = false;
		}

		public void put(char c, TrieNode node) {
			nodes[c - 'a'] = node;
		}

		public boolean containsKey(char c) {
			return nodes[c - 'a'] != null;
		}

		public TrieNode get(char c) {
			return nodes[c - 'a'];
		}

		public boolean isEnd() {
			return isEnd;
		}

		public void setEnd() {
			isEnd = true;
		}
	}
}