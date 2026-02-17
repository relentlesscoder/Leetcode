package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/28/2019.
 * #0063 https://leetcode.com/problems/unique-paths-ii/
 */
public class UniquePathsII {

	// time O(m * n), space O(n)
	public int uniquePathsWithObstaclesDP(int[][] obstacleGrid) {
		// 空间优化版 DP
		int m = obstacleGrid.length, n = obstacleGrid[0].length;
		int[] pre = new int[n + 1];
		pre[1] = 1;
		for (int i = 1; i <= m; i++) {
			int[] dp = new int[n + 1];
			for (int j = 1; j <= n; j++) {
				dp[j] = obstacleGrid[i - 1][j - 1] == 1 ? 0 : pre[j] + dp[j - 1];
			}
			pre = dp;
		}
		return pre[n];
	}

	// time O(m * n), space O(m * n)
	public int uniquePathsWithObstaclesDPWithGrid(int[][] obstacleGrid) {
		// #0062 相似题， 唯一的不同是如果格子有障碍物则路径为 0 。
		int m = obstacleGrid.length, n = obstacleGrid[0].length;
		int[][] dp = new int[m + 1][n + 1];
		dp[0][1] = 1;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] = obstacleGrid[i - 1][j - 1] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
			}
		}
		return dp[m][n];
	}

    // time O(m * n), space O(m * n)
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length, n = obstacleGrid[0].length;
		int[][] memo = new int[m][n];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return dfs(m - 1, n - 1, obstacleGrid, memo);
	}

	private int dfs(int i, int j, int[][] grid, int[][] memo) {
		if (i < 0 || j < 0) {
			return 0;
		}
		if (i == 0 && j == 0) { // 注意 grid[0][0] 有可能是 1
			return 1 - grid[0][0];
		}
		if (grid[i][j] == 1) {
			return 0;
		}
		if (memo[i][j] != -1) {
			return memo[i][j];
		}
		// 到达 grid[i][j] 的路径总和为从左 (i, j - 1) 或者从右来 (i - 1, j) 的路径
		// 相加, 因此可以分解为更小的子问题。
		return memo[i][j] = dfs(i - 1, j, grid, memo) + dfs(i, j - 1, grid, memo);
	}
}
