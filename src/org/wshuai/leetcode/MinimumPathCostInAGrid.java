package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/07/2023.
 * #2304 https://leetcode.com/problems/minimum-path-cost-in-a-grid/
 */
public class MinimumPathCostInAGrid {

	// time O((m * n) * n), space O(n)
	public int minPathCost(int[][] grid, int[][] moveCost) {
		int res = Integer.MAX_VALUE, m = grid.length, n = grid[0].length;
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			dp[i] = grid[m - 1][i];
		}
		for (int i = m - 2; i >= 0; i--) {
			int[] next = new int[n];
			Arrays.fill(next, Integer.MAX_VALUE);
			for (int j = 0; j < n; j++) {
				int[] costs = moveCost[grid[i][j]];
				for (int k = 0; k < costs.length; k++) {
					next[j] = Math.min(next[j], costs[k] + dp[k]);
				}
				next[j] += grid[i][j];
			}
			dp = next;
		}
		for (int j = 0; j < n; j++) {
			res = Math.min(res, dp[j]);
		}
		return res;
	}

	// time O((m * n) * n), space O(m * n)
	public int minPathCostTopDownDP(int[][] grid, int[][] moveCost) {
		int res = Integer.MAX_VALUE, m = grid.length, n = grid[0].length;
		int[][] costs = new int[m][n];
		for (int i = 0; i < m; i++) {
			Arrays.fill(costs[i], Integer.MAX_VALUE);
		}
		for (int i = 0; i < n; i++) {
			res = Math.min(res, dfs(0, i, grid, moveCost, m, n, costs));
		}
		return res;
	}

	private int dfs(int i, int j, int[][] grid, int[][] moveCost, int m, int n, int[][] dp) {
		if (i == m - 1) {
			return grid[i][j];
		}
		if (dp[i][j] != Integer.MAX_VALUE) {
			return dp[i][j];
		}
		int minCost = Integer.MAX_VALUE;
		for (int c = 0; c < n; c++) {
			minCost = Math.min(minCost, moveCost[grid[i][j]][c] + dfs(i + 1, c, grid, moveCost, m, n, dp));
		}
		dp[i][j] = grid[i][j] + minCost;
		return dp[i][j];
	}
}
