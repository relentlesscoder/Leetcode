package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2023.
 * #2596 https://leetcode.com/problems/check-knight-tour-configuration/
 */
public class CheckKnightTourConfiguration {

	// time O(n^n), space O(n^n)
	public boolean checkValidGrid(int[][] grid) {
		if (grid[0][0] != 0) {
			return false;
		}
		int n = grid.length, l = n * n, mask = (1 << 16) - 1;
		int[] map = new int[l];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[grid[i][j]] = (i << 16) + j; // since n is pretty small we can encode i and j into higher 16 bits and lower 16 bits respectively in one 32 bits integer
			}
		}
		for (int i = 1; i < l; i++) {
			int x1 = (map[i - 1] >> 16), x2 = (map[i] >> 16), y1 = (mask & map[i - 1]), y2 = (mask & map[i]);
			if (Math.abs(x1 - x2) * Math.abs(y1 - y2) != 2) {
				return false;
			}
		}
		return true;
	}

	private static final int[][] DIRS = new int[][]{
			{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}
	};

	// time O(n^n), space O(n^n)
	public boolean checkValidGridMatrixTraverse(int[][] grid) {
		if (grid[0][0] != 0) {
			return false;
		}
		int n = grid.length;
		return dfs(0, 0, 0, n * n - 1, n, grid);
	}

	private boolean dfs(int x, int y, int curr, int target, int n, int[][] grid) {
		if (grid[x][y] == target) {
			return true;
		}
		for (int i = 0; i < 8; i++) {
			int r = x + DIRS[i][0], c = y + DIRS[i][1];
			if (r >= 0 && r < n && c >= 0 && c < n && grid[r][c] == curr + 1) {
				return dfs(r, c, curr + 1, target, n, grid);
			}
		}
		return false;
	}
}
