package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 09/02/2020.
 * #1567
 * https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/
 */
public class MaximumLengthOfSubarrayWithPositiveProduct {

	// time O(n), space O(1)
	public int getMaxLenDP(int[] nums) {
		// 核心思路: 同时维护以当前位置结尾的正积最长长度(d0)和负积最长长度(d1)
		// 遇到负数时正负互换, 遇到0时全部重置
		int res = 0, n = nums.length, d0 = 0, d1 = 0;
		for (int i = 0; i < n; i++) {
			if (nums[i] == 0) {
				// 0会截断子数组, 正积和负积长度都重置
				d0 = d1 = 0;
				continue;
			}
			if (nums[i] > 0) {
				// 正数: 正积延长, 负积存在则也延长(负*正=负)
				d0 += 1;
				d1 = d1 > 0 ? d1 + 1 : 0;
			} else {
				// 负数: 正负互换. 旧正积*负数=新负积, 旧负积*负数=新正积
				int temp = d1;
				d1 = d0 + 1; // 旧正积+1变新负积(一定存在)
				d0 = temp > 0 ? temp + 1 : 0; // 旧负积存在才能变新正积
			}
			res = Math.max(res, d0);
		}
		return res;
	}

	// time O(n), space O(n)
	public int getMaxLenDPWithGrid(int[] nums) {
		// 核心思路: 与滚动变量版相同, 但保留完整二维数组
		// dp[i][0]: 以第i个元素结尾的正积最长子数组长度, dp[i][1]: 负积最长长度
		int res = 0, n = nums.length;
		int[][] dp = new int[n + 1][2];
		for (int i = 0; i < n; i++) {
			if (nums[i] == 0) {
				// 0截断, dp[i+1]默认为0
				continue;
			}
			if (nums[i] > 0) {
				// 正数: 正积延长, 负积存在则延长
				dp[i + 1][0] = dp[i][0] + 1;
				dp[i + 1][1] = dp[i][1] > 0 ? dp[i][1] + 1 : 0;
			} else {
				// 负数: 正负互换
				dp[i + 1][1] = dp[i][0] + 1;
				dp[i + 1][0] = dp[i][1] > 0 ? dp[i][1] + 1 : 0;
			}
			res = Math.max(res, dp[i + 1][0]);
		}
		return res;
	}

	// time O(n), space O(n)
	public int getMaxLenDFSWithMemorization(int[] nums) {
		// 核心思路: 记忆化DFS, 枚举每个位置作为子数组终点, 向左扩展求最长正积长度
		int res = 0, n = nums.length;
		// memo[i][j]: 以第i个元素结尾, j=0正积/j=1负积的最长子数组长度
		int[][] memo = new int[n][2];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		for (int i = 0; i < n; i++) {
			res = Math.max(res, dfs(i, 0, nums, memo));
		}
		return res;
	}

	// i: 当前位置, j: 0=求正积长度, 1=求负积长度
	private int dfs(int i, int j, int[] nums, int[][] memo) {
		if (i == -1) {
			return 0;
		}
		if (nums[i] == 0) {
			// 0截断子数组
			return 0;
		}
		if (memo[i][j] != -1) {
			return memo[i][j];
		}
		if (j == 0) {
			// 求正积: 正数直接延长正积, 负数需要前面有负积才能翻正
			if (nums[i] > 0) {
				return memo[i][j] = dfs(i - 1, 0, nums, memo) + 1;
			}
			int prev = dfs(i - 1, 1, nums, memo);
			return memo[i][j] = prev > 0 ? prev + 1 : 0;
		}
		// 求负积: 负数直接延长正积变负积, 正数需要前面有负积才能保持负
		if (nums[i] < 0) {
			return memo[i][j] = dfs(i - 1, 0, nums, memo) + 1;
		}
		int prev = dfs(i - 1, 1, nums, memo);
		return memo[i][j] = prev > 0 ? prev + 1 : 0;
	}
}
