package org.wshuai.leetcode;

/**
 * Created by Wei on 02/14/2017.
 * #0079 https://leetcode.com/problems/word-search/
 */
public class WordSearch {

	private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};

	// time O(m * n * 3^k), space O(k)
	public boolean exist(char[][] board, String word) {
		// #1219
		int m = board.length, n = board[0].length;
		char[] s = word.toCharArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == word.charAt(0) && dfs(0, i, j, s, board)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean dfs(int k, int i, int j, char[] s, char[][] grid) {
		if (k == s.length - 1) {
			return true;
		}
		char c = grid[i][j];
		grid[i][j] = '#';
		for (int d = 0; d < 4; d++) {
			int x = i + DIRS[d], y = j + DIRS[d + 1];
			if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length
					&& grid[x][y] == s[k + 1]) {
				if (dfs(k + 1, x, y, s, grid)) {
					return true;
				}
			}
		}
		// 递归完恢复现场
		grid[i][j] = c;
		return false;
	}
}
