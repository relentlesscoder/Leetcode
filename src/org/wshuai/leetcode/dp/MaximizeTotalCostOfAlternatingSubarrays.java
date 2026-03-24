package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 03/23/2026.
 * #3196
 * https://leetcode.com/problems/maximize-total-cost-of-alternating-subarrays/
 */
public class MaximizeTotalCostOfAlternatingSubarrays {

	// time O(n), space O(1)
	public long maximumTotalCostDP(int[] nums) {
		// 空间优化版 DP：只保留前两轮的状态，滚动更新
		int n = nums.length;
		long s1 = 0, s2 = nums[0];
		for (int i = 1; i < n; i++) {
			long s = Math.max(s2 + nums[i], s1 + nums[i - 1] - nums[i]);
			s1 = s2;
			s2 = s;
		}
		return s2;
	}

	// time O(n), space O(n)
	public long maximumTotalCostDPWithArray(int[] nums) {
		// 把记忆化搜索翻译成 DP
		int n = nums.length;
		long[] dp = new long[n + 1];
		dp[1] = nums[0];
		for (int i = 1; i < n; i++) {
			dp[i + 1] = Math.max(dp[i] + nums[i], dp[i - 1] + nums[i - 1] - nums[i]);
		}
		return dp[n];
	}

	// time O(n), space O(n)
	public long maximumTotalCostDFSWithMemorization(int[] nums) {
		// 划分型 DP：dfs(i) = nums[0..i] 的最大总 cost
		//
		// 关键观察：子数组长度最多为 2
		// 长度 >= 3 的子数组 [a, b, c] cost = a - b + c = cost([a, b]) + cost([c])
		// 拆成长度 1 和 2 的子数组，cost 完全相同，所以只需考虑长度 1 和 2
		//
		// 转移：枚举最后一段的长度
		// 长度 1：nums[i] 单独成段 → dfs(i-1) + nums[i]
		// 长度 2：nums[i-1], nums[i] 合成一段 → dfs(i-2) + nums[i-1] - nums[i]
		// 取两者最大值
		//
		// 什么时候合并更优？nums[i] < 0 时倾向合并（-nums[i] 变成加上绝对值）
		int n = nums.length;
		long[] memo = new long[n];
		Arrays.fill(memo, Long.MIN_VALUE);
		return dfs(n - 1, nums, memo);
	}

	// dfs(i) = nums[0..i] 的最大总 cost
	private long dfs(int i, int[] nums, long[] memo) {
		if (i == -1) {
			return 0L; // 没有元素，cost 为 0
		}
		if (i == 0) {
			return (long) nums[0]; // 只有一个元素，必须单独成段，cost = nums[0]
		}
		if (memo[i] != Long.MIN_VALUE) {
			return memo[i];
		}
		return memo[i] = Math.max(
				dfs(i - 1, nums, memo) + nums[i], // 长度 1：nums[i] 单独成段，+nums[i]
				dfs(i - 2, nums, memo) + nums[i - 1] - nums[i] // 长度 2：和前一个合并，+nums[i-1] - nums[i]
		);
	}
}
