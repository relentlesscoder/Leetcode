package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 03/08/2026.
 * #2431
 * https://leetcode.com/problems/maximize-total-tastiness-of-purchased-fruits/
 */
public class MaximizeTotalTastinessOfPurchasedFruits {

	// time O(n * c * a), space O(c * a)
	public int maxTastiness(int[] price, int[] tastiness, int maxAmount, int maxCoupons) {
		// 空间优化版 DP
		int n = price.length;
		int[][] dp = new int[maxCoupons + 1][maxAmount + 1];
		for (int i = 0; i < n; i++) {
			int p = price[i], t = tastiness[i];
			for (int c = maxCoupons; c >= 0; c--) {
				for (int a = maxAmount; a >= 0; a--) {
					if (a >= p) {
						dp[c][a] = Math.max(dp[c][a], dp[c][a - p] + t);
					}
					if (c > 0 && a >= p / 2) {
						dp[c][a] = Math.max(dp[c][a], dp[c - 1][a - p / 2] + t);
					}
				}
			}
		}
		return dp[maxCoupons][maxAmount];
	}

	// time O(n * c * a), space O(n * c * a)
	public int maxTastinessDPWithGrid(int[] price, int[] tastiness, int maxAmount, int maxCoupons) {
		// 将记忆化搜索翻译成 DP
		int n = price.length;
		int[][][] dp = new int[n + 1][maxCoupons + 1][maxAmount + 1];
		for (int i = 0; i < n; i++) {
			int p = price[i], t = tastiness[i];
			for (int c = maxCoupons; c >= 0; c--) {
				for (int a = maxAmount; a >= 0; a--) {
					dp[i + 1][c][a] = dp[i][c][a];
					if (a >= p) {
						dp[i + 1][c][a] = Math.max(dp[i + 1][c][a], dp[i][c][a - p] + t);
					}
					if (c > 0 && a >= p / 2) {
						dp[i + 1][c][a] = Math.max(dp[i + 1][c][a], dp[i][c - 1][a - p / 2] + t);
					}
				}
			}
		}
		return dp[n][maxCoupons][maxAmount];
	}

	// time O(n * c * a), space O(n * c * a)
	public int maxTastinessDFSWithMemorization(int[] price, int[] tastiness, int maxAmount, int maxCoupons) {
		// 记忆化搜索
		int n = price.length;
		int[][][] memo = new int[n][maxCoupons + 1][maxAmount + 1];
		for (int[][] matrix : memo) {
			for (int[] row : matrix) {
				Arrays.fill(row, -1);
			}
		}
		return dfs(n - 1, maxAmount, maxCoupons, price, tastiness, memo);
	}

	private int dfs(int i, int a, int c, int[] price, int[] taste, int[][][] memo) {
		if (i == -1) {
			return 0;
		}
		if (memo[i][c][a] != -1) {
			return memo[i][c][a];
		}
		// 不买
		int p = price[i], t = taste[i], res = dfs(i - 1, a, c, price, taste, memo);
		if (a >= p) { // 买
			res = Math.max(res, dfs(i - 1, a - p, c, price, taste, memo) + t);
		}
		if (c > 0 && a >= p / 2) { // 买 + 用券
			res = Math.max(res, dfs(i - 1, a - p / 2, c - 1, price, taste, memo) + t);
		}
		return memo[i][c][a] = res;
	}
}
