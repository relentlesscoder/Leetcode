package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 04/15/2026.
 * #2708
 * https://leetcode.com/problems/maximum-strength-of-a-group/
 */
public class MaximumStrengthOfAGroup {

	// time O(n), space O(1)
	public long maxStrengthDP(int[] nums) {
		// 核心思路: 类似最大子数组积, 同时维护当前最大积和最小积
		// 因为负数乘负数变正数, 最小积(负的最大绝对值)乘负数可能变最大积
		int n = nums.length;
		// d0: 以前i个元素子集能得到的最大积, d1: 最小积
		long d0 = nums[0], d1 = nums[0];
		for (int i = 1; i < n; i++) {
			// p1: 最小积 * nums[i], p2: 最大积 * nums[i]
			long p1 = d1 * nums[i], p2 = d0 * nums[i];
			// 最大积: 从(最小积*nums[i], 最大积*nums[i], 不选nums[i], 只选nums[i])中取最大
			d0 = Math.max(p1, Math.max(p2, Math.max(d0, nums[i])));
			// 最小积: 同样四种选择取最小
			d1 = Math.min(p1, Math.min(p2, Math.min(d1, nums[i])));
		}
		return d0;
	}

	// time O(n), space O(n)
	public long maxStrengthDPWithArray(int[] nums) {
		// 核心思路: 与滚动变量版相同, 但保留完整二维数组
		// dp[i][0]: 前i+1个元素子集的最大积, dp[i][1]: 最小积
		int n = nums.length;
		long[][] dp = new long[n][2];
		dp[0][0] = dp[0][1] = nums[0]; // 只有一个元素时最大积和最小积都是它本身
		for (int i = 1; i < n; i++) {
			long p1 = dp[i - 1][1] * nums[i], p2 = dp[i - 1][0] * nums[i];
			// 最大积: 最小积*nums[i], 最大积*nums[i], 不选nums[i], 只选nums[i]
			dp[i][0] = Math.max(p1, Math.max(p2, Math.max(dp[i - 1][0], nums[i])));
			// 最小积: 同样四种选择取最小
			dp[i][1] = Math.min(p1, Math.min(p2, Math.min(dp[i - 1][1], nums[i])));
		}
		return dp[n - 1][0];
	}
}
