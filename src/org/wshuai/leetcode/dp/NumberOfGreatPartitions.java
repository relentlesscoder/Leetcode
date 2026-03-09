package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 03/08/2026.
 * #2518 https://leetcode.com/problems/number-of-great-partitions/
 */
public class NumberOfGreatPartitions {

	private static final int MOD = (int) 1e9 + 7;

	// time O(n * k), space O(k)
	public int countPartitions(int[] nums, int k) {
		// 空间优化版 DP
		int n = nums.length;
		long total = 0;
		for (int x : nums) {
			total += x;
		}
		if (total < 2 * k) {
			return 0;
		}
		int[] dp = new int[k + 1];
		Arrays.fill(dp, 1);
		dp[0] = 0;
		for (int i = 0; i < n; i++) {
			for (int c = k; c >= 0; c--) {
				dp[c] = (dp[c] + (c > nums[i] ? dp[c - nums[i]] : 0)) % MOD;
			}
		}
		return (int) ((pow(n, 2L) - 2L * dp[k] % MOD + MOD) % MOD);
	}

	// time O(n * k), space O(n * k)
	public int countPartitionsDPWithGrid(int[] nums, int k) {
		// 将记忆化搜索翻译成 DP
		int n = nums.length;
		long total = 0;
		for (int x : nums) {
			total += x;
		}
		if (total < 2 * k) {
			return 0;
		}
		int[][] dp = new int[n + 1][k + 1];
		Arrays.fill(dp[0], 1);
		dp[0][0] = 0;
		for (int i = 0; i < n; i++) {
			for (int c = k; c >= 0; c--) {
				dp[i + 1][c] = (dp[i][c] + (c > nums[i] ? dp[i][c - nums[i]] : 0)) % MOD;
			}
		}
		return (int) ((pow(n, 2L) - 2L * dp[n][k] % MOD + MOD) % MOD);
	}

	// time O(n * k), space O(n * k)
	public int countPartitionsDFSWithMemorization(int[] nums, int k) {
		// 记忆化搜索
		int n = nums.length;
		long total = 0;
		for (int x : nums) {
			total += x;
		}
		// 如果总和小于 2 * k，那么无论怎么分都不满足条件，直接返回 0。
		if (total < 2 * k) {
			return 0;
		}
		int[][] memo = new int[n][k + 1];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return (int) ((pow(n, 2L) - 2L * dfs(n - 1, k, nums, memo) % MOD + MOD) % MOD);
	}

	private int dfs(int i, int s, int[] nums, int[][] memo) {
		if (s <= 0) {
			return 0;
		}
		if (i == -1) {
			return 1;
		}
		if (memo[i][s] != -1) {
			return memo[i][s];
		}
		// 不选 nums[i] 或者选 nums[i]
		return memo[i][s] = (dfs(i - 1, s, nums, memo) + dfs(i - 1, s - nums[i], nums, memo)) % MOD;
	}

	private int pow(int n, long x) {
		// 快速幂
		long res = 1L;
		while (n > 0) {
			if (n % 2 == 1) {
				res = res * x % MOD;
			}
			x = x * x % MOD;
			n /= 2;
		}
		return (int) res;
	}
}
