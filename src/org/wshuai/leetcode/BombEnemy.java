package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0361 https://leetcode.com/problems/bomb-enemy/
 */
public class BombEnemy {
	// time O(m*n), space O(m*n*4)
	public int maxKilledEnemies(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int res = 0, r = grid.length, c = grid[0].length;
		int[][] dp = new int[r][c];
		for (int i = 0; i < r; i++) {
			int left = 0;
			for (int j = 0; j < c; j++) {
				if (grid[i][j] == 'W') {
					left = 0;
				}
				if (grid[i][j] == 'E') {
					left++;
				}
				if (grid[i][j] == '0') {
					dp[i][j] += left;
				}
			}
		}
		for (int j = 0; j < c; j++) {
			int up = 0;
			for (int i = 0; i < r; i++) {
				if (grid[i][j] == 'W') {
					up = 0;
				}
				if (grid[i][j] == 'E') {
					up++;
				}
				if (grid[i][j] == '0') {
					dp[i][j] += up;
				}
			}
		}
		for (int i = r - 1; i >= 0; i--) {
			int right = 0;
			for (int j = c - 1; j >= 0; j--) {
				if (grid[i][j] == 'W') {
					right = 0;
				}
				if (grid[i][j] == 'E') {
					right++;
				}
				if (grid[i][j] == '0') {
					dp[i][j] += right;
				}
			}
		}
		for (int j = c - 1; j >= 0; j--) {
			int bottom = 0;
			for (int i = r - 1; i >= 0; i--) {
				if (grid[i][j] == 'W') {
					bottom = 0;
				}
				if (grid[i][j] == 'E') {
					bottom++;
				}
				if (grid[i][j] == '0') {
					dp[i][j] += bottom;
					res = Math.max(res, dp[i][j]);
				}
			}
		}
		return res;
	}
}
