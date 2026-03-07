package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 11/07/2016.
 * #0322 https://leetcode.com/problems/coin-change/
 */
public class CoinChange {

	private static final int MAX = Integer.MAX_VALUE / 2;

	// time O(n * t), space O(t)
	public int coinChangeDP(int[] coins, int amount) {
		// 进一步优化空间
		int n = coins.length;
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, MAX);
		dp[0] = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= amount; j++) {
				dp[j] = j < coins[i] ? dp[j] : Math.min(dp[j], dp[j - coins[i]] + 1);
			}
		}
		return dp[amount] < MAX ? dp[amount] : -1;
	}

	// time O(n * t), space O(t)
	public int coinChangeDPWithTwoArrays(int[] coins, int amount) {
		// 空间优化版 DP
		int n = coins.length;
		int[] pre = new int[amount + 1];
		Arrays.fill(pre, MAX);
		pre[0] = 0;
		for (int i = 0; i < n; i++) {
			int[] dp = new int[amount + 1];
			for (int j = 0; j <= amount; j++) {
				dp[j] = j < coins[i] ? pre[j] : Math.min(pre[j], dp[j - coins[i]] + 1);
			}
			pre = dp;
		}
		return pre[amount] < MAX ? pre[amount] : -1;
	}

	// time O(n * t), space O(n * t)
	public int coinChangeDPWithGrid(int[] coins, int amount) {
		// 把记忆化搜索翻译成 DP
		int n = coins.length;
		int[][] dp = new int[n + 1][amount + 1];
		Arrays.fill(dp[0], MAX);
		dp[0][0] = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= amount; j++) {
				dp[i + 1][j] = j < coins[i] ? dp[i][j] : Math.min(dp[i][j], dp[i + 1][j - coins[i]] + 1);
			}
		}
		return dp[n][amount] < MAX ? dp[n][amount] : -1;
	}

	// time O(n * t), space O(n * t)
	public int coinChangeDFSWithMemorization(int[] coins, int amount) {
		// 记忆化搜索 - 完全背包
		int n = coins.length;
		int[][] memo = new int[n][amount + 1];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		int res = dfs(n - 1, amount, coins, memo);
		return res < MAX ? res : -1;
	}

	private int dfs(int i, int t, int[] nums, int[][] memo) {
		if (i == -1) {
			return t == 0 ? 0 : MAX;
		}
		if (memo[i][t] != -1) {
			return memo[i][t];
		}
		if (t < nums[i]) {
			return memo[i][t] = dfs(i - 1, t, nums, memo);
		} else {
			return memo[i][t] = Math.min(dfs(i - 1, t, nums, memo), dfs(i, t - nums[i], nums, memo) + 1);
		}
	}
}
