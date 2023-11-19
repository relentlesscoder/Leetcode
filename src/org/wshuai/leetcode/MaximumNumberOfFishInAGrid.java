package org.wshuai.leetcode;

/**
 * Created by Wei on 09/30/2023.
 * #2658 https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/
 */
public class MaximumNumberOfFishInAGrid {

	private int[] dirs = new int[]{0, -1, 0, 1, 0};

	// time O(m * n), space O(m + n)
	public int findMaxFish(int[][] grid) {
		int res = 0, m = grid.length, n = grid[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] > 0) {
					res = Math.max(res, dfs(grid, m, n, i, j));
				}
			}
		}
		return res;
	}

	private int dfs(int[][] grid, int m, int n, int i, int j) {
		int sum = grid[i][j];
		grid[i][j] = 0;
		for (int k = 0; k < 4; k++) {
			int x = i + dirs[k], y = j + dirs[k + 1];
			if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
				sum += dfs(grid, m, n, x, y);
			}
		}
		return sum;
	}
}
