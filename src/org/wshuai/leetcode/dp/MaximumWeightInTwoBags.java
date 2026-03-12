package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 03/08/2026.
 * #3647 https://leetcode.com/problems/maximum-weight-in-two-bags/
 */
public class MaximumWeightInTwoBags {

	// time O(n * w1 * w2), space O(w1 * w2)
	public int maxWeight(int[] weights, int w1, int w2) {
		// 空间优化版 DP
		int n = weights.length;
		int[][] dp = new int[w1 + 1][w2 + 1];
		for (int i = w1; i >= 0; i--) {
			for (int j = w2; j >= 0; j--) {
				dp[i][j] = i + j;
			}
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = w1; j >= 0; j--) {
				for (int k = w2; k >= 0; k--) {
					if (j >= weights[i]) {
						dp[j][k] = Math.min(dp[j][k], dp[j - weights[i]][k]);
					}
					if (k >= weights[i]) {
						dp[j][k] = Math.min(dp[j][k], dp[j][k - weights[i]]);
					}
				}
			}
		}
		return w1 + w2 - dp[w1][w2];
	}

	// time O(n * w1 * w2), space O(n * w1 * w2)
	public int maxWeightDPWithGrid(int[] weights, int w1, int w2) {
		// 将记忆化搜索翻译成 DP
		int n = weights.length;
		int[][][] dp = new int[n + 1][w1 + 1][w2 + 1];
		for (int i = w1; i >= 0; i--) {
			for (int j = w2; j >= 0; j--) {
				dp[n][i][j] = i + j;
			}
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = w1; j >= 0; j--) {
				for (int k = w2; k >= 0; k--) {
					dp[i][j][k] = dp[i + 1][j][k];
					if (j >= weights[i]) {
						dp[i][j][k] = Math.min(dp[i][j][k], dp[i + 1][j - weights[i]][k]);
					}
					if (k >= weights[i]) {
						dp[i][j][k] = Math.min(dp[i][j][k], dp[i + 1][j][k - weights[i]]);
					}
				}
			}
		}
		return w1 + w2 - dp[0][w1][w2];
	}

	// time O(n * w1 * w2), space O(n * w1 * w2)
	public int maxWeightDFSWithMemorization(int[] weights, int w1, int w2) {
		// 记忆化搜索
		int n = weights.length;
		// 先排序，这样可以更快地剪枝
		Arrays.sort(weights);
		int[][][] memo = new int[n][w1 + 1][w2 + 1];
		for (int[][] matrix : memo) {
			for (int[] row : matrix) {
				Arrays.fill(row, -1);
			}
		}
		return w1 + w2 - dfs(0, w1, w2, weights, memo);
	}

	private int dfs(int i, int w1, int w2, int[] weights, int[][][] memo) {
		// 终止条件：1. 所有物品都考虑完了；2. 当前物品太重了，装不下了
		if (i == weights.length || Math.max(w1, w2) < weights[i]) {
			return w1 + w2;
		}
		if (memo[i][w1][w2] != -1) {
			return memo[i][w1][w2];
		}
		int res = dfs(i + 1, w1, w2, weights, memo); // 不装
		if (w1 >= weights[i]) { // 装在第一袋
			res = Math.min(res, dfs(i + 1, w1 - weights[i], w2, weights, memo));
		}
		if (w2 >= weights[i]) { // 装在第二袋
			res = Math.min(res, dfs(i + 1, w1, w2 - weights[i], weights, memo));
		}
		return memo[i][w1][w2] = res;
	}
}
