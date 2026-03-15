package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 03/12/2026.
 * #3183
 * https://leetcode.com/problems/the-number-of-ways-to-make-the-sum/
 */
public class TheNumberOfWaysToMakeTheSum {

	private static final int MOD = (int) 1e9 + 7;

	// time O(3 * n), space O(n)
	public int numberOfWays(int n) {
		// 空间优化版 DP
		int[] coins = new int[] { 6, 2, 1 };
		int[] dp = new int[n + 1];
		dp[0] = 1;
		for (int i = 0; i < 3; i++) {
			for (int s = 1; s <= n; s++) {
				if (coins[i] > s) {
					dp[s] = dp[s];
				} else {
					dp[s] = (dp[s] + dp[s - coins[i]]) % MOD;
				}
			}
		}
		int res = dp[n];
		if (n >= 4) {
			res = (res + dp[n - 4]) % MOD;
		}
		if (n >= 8) {
			res = (res + dp[n - 8]) % MOD;
		}
		return res;
	}

	// time O(3 * n), space O(3 * n)
	public int numberOfWaysDPWithGrid(int n) {
		// 把记忆化搜索翻译成 DP
		int[] coins = new int[] { 6, 2, 1 };
		int[][] dp = new int[4][n + 1];
		for (int i = 0; i < 4; i++) {
			dp[i][0] = 1;
		}
		for (int i = 0; i < 3; i++) {
			for (int s = 1; s <= n; s++) {
				if (coins[i] > s) {
					dp[i + 1][s] = dp[i][s];
				} else {
					dp[i + 1][s] = (dp[i][s] + dp[i + 1][s - coins[i]]) % MOD;
				}
			}
		}
		int res = dp[3][n];
		if (n >= 4) {
			res = (res + dp[3][n - 4]) % MOD;
		}
		if (n >= 8) {
			res = (res + dp[3][n - 8]) % MOD;
		}
		return res;
	}

	// time O(3 * n), space O(3 * n)
	public int numberOfWaysDFSWithMemorization(int n) {
		int[] coins = new int[] { 6, 2, 1 };
		int[][] memo = new int[3][n + 1];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		int res = dfs(2, n, coins, memo);
		if (n >= 4) {
			res = (res + dfs(2, n - 4, coins, memo)) % MOD;
		}
		if (n >= 8) {
			res = (res + dfs(2, n - 8, coins, memo)) % MOD;
		}
		return res;
	}

	private int dfs(int i, int n, int[] nums, int[][] memo) {
		if (n == 0) {
			return 1;
		}
		if (i == -1 || nums[i] > n) {
			return 0;
		}
		if (memo[i][n] != -1) {
			return memo[i][n];
		}
		return memo[i][n] = (dfs(i - 1, n, nums, memo)
				+ dfs(i, n - nums[i], nums, memo)) % MOD;
	}
}
