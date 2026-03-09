package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 03/08/2026.
 * #2291 https://leetcode.com/problems/maximum-profit-from-trading-stocks/
 */
public class MaximumProfitFromTradingStocks {

	// time O(n * b), space O(b)
	public int maximumProfit(int[] present, int[] future, int budget) {
		// 空间优化版 DP
		int n = future.length;
		int[] dp = new int[budget + 1];
		for (int i = 0; i < n; i++) {
			int profit = future[i] - present[i];
			for (int b = budget; b >= 0; b--) {
				if (b >= present[i] && present[i] < future[i]) {
					dp[b] = Math.max(dp[b], dp[b - present[i]] + profit);
				}
			}
		}
		return dp[budget];
	}

	// time O(n * b), space O(n * b)
	public int maximumProfitDPWithGrip(int[] present, int[] future, int budget) {
		// 将记忆化搜索翻译成 DP
		int n = future.length;
		int[][] dp = new int[n + 1][budget + 1];
		for (int i = 0; i < n; i++) {
			int profit = future[i] - present[i];
			for (int b = budget; b >= 0; b--) {
				if (b < present[i] || present[i] >= future[i]) {
					dp[i + 1][b] = dp[i][b];
				} else {
					dp[i + 1][b] = Math.max(dp[i][b], dp[i][b - present[i]] + profit);
				}
			}
		}
		return dp[n][budget];
	}

	// time O(n * b), space O(n * b)
	public int maximumProfitDFSWithMemorization(int[] present, int[] future, int budget) {
		// 记忆化搜索
		int n = future.length;
		int[][] memo = new int[n][budget + 1];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return dfs(n - 1, budget, present, future, memo);
	}

	private int dfs(int i, int b, int[] present, int[] future, int[][] memo) {
		if (i == -1) {
			return 0;
		}
		if (memo[i][b] != -1) {
			return memo[i][b];
		}
		if (b < present[i] || present[i] >= future[i]) { // 买不起或者不赚钱
			return memo[i][b] = dfs(i - 1, b, present, future, memo);
		} else { // 买得起而且赚钱 - 不买或者买
			return memo[i][b] = Math.max(dfs(i - 1, b, present, future, memo),
					dfs(i - 1, b - present[i], present, future, memo) + future[i] - present[i]);
		}
	}
}
