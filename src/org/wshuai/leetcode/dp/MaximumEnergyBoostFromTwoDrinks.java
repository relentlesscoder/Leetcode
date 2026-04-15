package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 04/12/2026.
 * #3259
 * https://leetcode.com/problems/maximum-energy-boost-from-two-drinks/
 */
public class MaximumEnergyBoostFromTwoDrinks {

	// time O(n), space O(1)
	public long maxEnergyBoostDP(int[] energyDrinkA, int[] energyDrinkB) {
		// 核心思路: 状态机DP, 每小时选择喝A或B, 切换饮料需跳过一小时
		// d1[j] = 第i-1小时喝饮料j的最大能量, d0[j] = 第i-2小时喝饮料j的最大能量
		int n = energyDrinkA.length;
		// d0: 前前轮, d1: 前一轮
		long[] d0 = new long[2], d1 = new long[] { energyDrinkA[0], energyDrinkB[0] };
		for (int i = 1; i < n; i++) {
			long[] c = new long[2];
			// 喝A: 上一轮也喝A(连续)或前前轮喝B(切换, 跳过上一轮)
			c[0] = Math.max(d1[0], d0[1]) + energyDrinkA[i];
			// 喝B: 上一轮也喝B(连续)或前前轮喝A(切换, 跳过上一轮)
			c[1] = Math.max(d1[1], d0[0]) + energyDrinkB[i];
			d0 = d1;
			d1 = c;
		}
		return Math.max(d1[0], d1[1]);
	}

	// time O(n), space O(n)
	public long maxEnergyBoostDPWithGrid(int[] energyDrinkA, int[] energyDrinkB) {
		// 核心思路: 与滚动数组版相同, 但保留完整二维数组
		// dp[i][j]: 前i小时, 第i小时喝饮料j的最大能量
		int n = energyDrinkA.length;
		long[][] dp = new long[n + 1][3];
		dp[1][0] = energyDrinkA[0];
		dp[1][1] = energyDrinkB[0];
		for (int i = 1; i < n; i++) {
			// 喝A: 上一轮也喝A(连续)或前前轮喝B(切换, 跳过上一轮)
			dp[i + 1][0] = Math.max(dp[i][0], dp[i - 1][1]) + energyDrinkA[i];
			// 喝B: 上一轮也喝B(连续)或前前轮喝A(切换, 跳过上一轮)
			dp[i + 1][1] = Math.max(dp[i][1], dp[i - 1][0]) + energyDrinkB[i];
		}
		return Math.max(dp[n][0], dp[n][1]);
	}

	// time O(n), space O(n)
	public long maxEnergyBoostDFSWithMemorization(int[] energyDrinkA, int[] energyDrinkB) {
		// 核心思路: 记忆化DFS, 从最后一小时往前递推, 每步选择喝A或B
		int n = energyDrinkA.length;
		// memo[i][j]: 前i+1小时, 第i小时喝饮料j的最大能量
		long[][] memo = new long[n][2];
		for (long[] row : memo) {
			Arrays.fill(row, -1L);
		}
		// 最后一小时喝A或喝B取较大值
		return Math.max(dfs(n - 1, 0, energyDrinkA, energyDrinkB, memo),
				dfs(n - 1, 1, energyDrinkA, energyDrinkB, memo));
	}

	// i: 当前小时索引, j: 当前选择的饮料(0=A, 1=B)
	private long dfs(int i, int j, int[] arr1, int[] arr2, long[][] memo) {
		if (i < 0) {
			return 0L;
		}
		if (memo[i][j] != -1L) {
			return memo[i][j];
		}
		if (j == 0) {
			// 喝A: 上一轮也喝A(连续), 或前前轮喝B(切换, 跳过i-1)
			return memo[i][j] = Math.max(dfs(i - 1, 0, arr1, arr2, memo), dfs(i - 2, 1, arr1, arr2, memo)) + arr1[i];
		}
		// 喝B: 上一轮也喝B(连续), 或前前轮喝A(切换, 跳过i-1)
		return memo[i][j] = Math.max(dfs(i - 1, 1, arr1, arr2, memo), dfs(i - 2, 0, arr1, arr2, memo)) + arr2[i];
	}
}
