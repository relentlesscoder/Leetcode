package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 03/14/2026.
 * #3290
 * https://leetcode.com/problems/maximum-multiplication-score/
 */
public class MaximumMultiplicationScore {

	// time O(m * n), space O(n)
	public long maxScore(int[] a, int[] b) {
		// 空间优化版 DP
		int m = a.length, n = b.length;
		long[] dp = new long[n + 1];
		for (int i = 0; i < m; i++) {
			long pre = dp[0];
			dp[0] = Long.MIN_VALUE / 2;
			for (int j = 0; j < n; j++) {
				long x = dp[j + 1];
				dp[j + 1] = Math.max(1L * a[i] * b[j] + pre, dp[j]);
				pre = x;
			}
		}
		return dp[n];
	}

	// time O(m * n), space O(m * n)
	public long maxScoreDPWithGrid(int[] a, int[] b) {
		// 把记忆化搜索翻译成 DP
		int m = a.length, n = b.length;
		long[][] dp = new long[m + 1][n + 1];
		for (int i = 0; i < m; i++) {
			dp[i + 1][0] = Long.MIN_VALUE / 2;
			for (int j = 0; j < n; j++) {
				dp[i + 1][j + 1] = Math.max(1L * a[i] * b[j] + dp[i][j], dp[i + 1][j]);
			}
		}
		return dp[m][n];
	}

	// time O(m * n), space O(m * n)
	public long maxScoreDFSWithMemorization(int[] a, int[] b) {
		// 记忆化搜索
		int m = a.length, n = b.length;
		long[][] memo = new long[m + 1][n + 1];
		for (long[] row : memo) {
			Arrays.fill(row, Long.MIN_VALUE);
		}
		return dfs(m - 1, n - 1, a, b, memo);
	}

	private long dfs(int i, int j, int[] a, int[] b, long[][] memo) {
		if (i == -1) {
			return 0L;
		}
		if (j == -1) {
			return Long.MIN_VALUE / 2;
		}
		if (memo[i][j] != Long.MIN_VALUE) {
			return memo[i][j];
		}
		return memo[i][j] = Math.max(1L * a[i] * b[j] + dfs(i - 1, j - 1, a, b, memo),
				dfs(i, j - 1, a, b, memo));
	}
}
