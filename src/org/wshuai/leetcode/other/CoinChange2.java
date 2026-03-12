package org.wshuai.leetcode.other;

import java.util.Arrays;

/**
 * Created by Wei on 09/24/2019.
 * #0518 https://leetcode.com/problems/coin-change-2/
 */
public class CoinChange2 {

	// time O(n * a), space O(a)
	public int change(int amount, int[] coins) {
		// 完全背包问题 - 空间优化版 DP
		int n = coins.length;
		int[] dp = new int[amount + 1];
		dp[0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= amount; j++) {
				if (j >= coins[i]) {
					dp[j] += dp[j - coins[i]];
				}
			}
		}
		return dp[amount];
	}

	// time O(n * t), space O(n * t)
	public int coinChangeDPWithGrid(int[] coins, int amount) {
		// 把记忆化搜索翻译成 DP
		int n = coins.length;
		int[][] dp = new int[n + 1][amount + 1];
		dp[0][0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= amount; j++) {
				dp[i + 1][j] = j < coins[i] ? dp[i][j] : dp[i][j] + dp[i + 1][j - coins[i]];
			}
		}
		return dp[n][amount];
	}

	// time O(n * a), space O(n * a)
	public int coinChangeDFSWithMemorization(int[] coins, int amount) {
		// 记忆化搜索 - 完全背包
		int n = coins.length;
		int[][] memo = new int[n][amount + 1];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return dfs(n - 1, amount, coins, memo);
	}

	private int dfs(int i, int t, int[] nums, int[][] memo) {
		if (i == -1) {
			return t == 0 ? 1 : 0;
		}
		if (memo[i][t] != -1) {
			return memo[i][t];
		}
		if (t < nums[i]) {
			return memo[i][t] = dfs(i - 1, t, nums, memo);
		} else {
			return memo[i][t] = dfs(i - 1, t, nums, memo) + dfs(i, t - nums[i], nums, memo);
		}
	}
}
