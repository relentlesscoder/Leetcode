package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 09/27/2016.
 * #0279 https://leetcode.com/problems/perfect-squares/
 */
public class PerfectSquares {

	private static final int MIN = 10_000;

	// time O(n * m), space O(n)
	public int numSquares(int n) {
		// 空间优化版 DP
		int m = (int) Math.sqrt(n);
		int[] dp = new int[n + 1];
		Arrays.fill(dp, MIN);
		dp[0] = 0;
		for (int i = 0; i < m; i++) {
			int p = (i + 1) * (i + 1);
			for (int x = 1; x <= n; x++) {
				if (x >= p) {
					dp[x] = Math.min(dp[x], 1 + dp[x - p]);
				}
			}
		}
		return dp[n];
	}

	// time O(n * m), space O(n * m)
	public int numSquaresDPWithGrid(int n) {
		// 将记忆化搜索翻译成 DP
		int m = (int) Math.sqrt(n);
		int[][] dp = new int[m + 1][n + 1];
		Arrays.fill(dp[0], MIN);
		dp[0][0] = 0;
		for (int i = 0; i < m; i++) {
			int p = (i + 1) * (i + 1);
			for (int x = 1; x <= n; x++) {
				if (x < p) {
					dp[i + 1][x] = dp[i][x];
				} else {
					dp[i + 1][x] = Math.min(dp[i][x], 1 + dp[i + 1][x - p]);
				}
			}
		}
		return dp[m][n];
	}

	// time O(n * m), space O(n * m)
	public int numSquaresDFSWithMemorization(int n) {
		// 记忆化搜索
		int m = (int) Math.sqrt(n);
		int[][] memo = new int[m + 1][n + 1];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return dfs(m, n, memo);
	}

	private int dfs(int i, int x, int[][] memo) {
		if (x == 0) { // 组成 x 需要 0 个完全平方数
			return 0;
		}
		if (i == 0) { // 数字已经用完无法组成 x，返回一个不合法的大数
			return MIN;
		}
		if (memo[i][x] != -1) {
			return memo[i][x];
		}
		int p = i * i;
		if (x < p) { // 不能选完全平方数 p，只能不选
			return memo[i][x] = dfs(i - 1, x, memo);
		} else { // 可以选完全平方数 p，选或者不选两种情况的较小值
			return memo[i][x] = Math.min(dfs(i - 1, x, memo), 1 + dfs(i, x - p, memo));
		}
	}
}
