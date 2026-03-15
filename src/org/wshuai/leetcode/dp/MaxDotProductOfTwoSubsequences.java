package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 05/24/2020.
 * #1458 https://leetcode.com/problems/max-dot-product-of-two-subsequences/
 */
public class MaxDotProductOfTwoSubsequences {

	// time O(m * n), space O(n)
	public int maxDotProduct(int[] nums1, int[] nums2) {
		// 空间优化版 DP
		int m = nums1.length, n = nums2.length;
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MIN_VALUE);
		for (int i = 0; i < m; i++) {
			int pre = dp[0];
			for (int j = 0; j < n; j++) {
				int x = dp[j + 1];
				int val = Math.max(0, pre) + nums1[i] * nums2[j];
				dp[j + 1] = Math.max(val, Math.max(dp[j + 1], dp[j]));
				pre = x;
			}
		}
		return dp[n];
	}

	// time O(m * n), space O(m * n)
	public int maxDotProductDPWithGrid(int[] nums1, int[] nums2) {
		// 把记忆化搜索翻译成 DP
		int m = nums1.length, n = nums2.length;
		int[][] dp = new int[m + 1][n + 1];
		for (int[] row : dp) {
			Arrays.fill(row, Integer.MIN_VALUE);
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dp[i + 1][j + 1] = Math.max(0, dp[i][j]) + nums1[i] * nums2[j];
				dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], Math.max(dp[i][j + 1], dp[i + 1][j]));
			}
		}
		return dp[m][n];
	}

	// time O(m * n), space O(m * n)
	public int maxDotProductDFSWithMemorization(int[] nums1, int[] nums2) {
		// 记忆化搜索
		int m = nums1.length, n = nums2.length;
		int[][] memo = new int[m][n];
		for (int[] row : memo) {
			Arrays.fill(row, Integer.MIN_VALUE);
		}
		return dfs(m - 1, n - 1, nums1, nums2, memo);
	}

	private int dfs(int i, int j, int[] nums1, int[] nums2, int[][] memo) {
		if (i == -1 || j == -1) { // 其中一个数组为空，无法得到任何点积
			return Integer.MIN_VALUE;
		}
		if (memo[i][j] != Integer.MIN_VALUE) {
			return memo[i][j];
		}
		// 选择连接 nums1[i] 和 nums2[j]，得到的点积为 nums1[i] * nums2[j] 加上连接前面元素的最大点积
		// 注意 max(0, dfs(i - 1, j - 1)) 的作用，如果连接前面元素的最大点积为负数，则不连接前面元素
		// 也可以保证只要选了至少一对元素则不会用到 Integer.MIN_VALUE 的值 - 从而满足子序列不为空的要求
		int res = nums1[i] * nums2[j] + Math.max(0, dfs(i - 1, j - 1, nums1, nums2, memo));
		// 1. 选择不连接 nums1[i]，看看连接 nums1[i - 1] 和 nums2[j] 能得到更大的点积
		// 2. 选择不连接 nums2[j]，看看连接 nums1[i] 和 nums2[j - 1] 能得到更大的点积
		return memo[i][j] = Math.max(res,
				Math.max(dfs(i - 1, j, nums1, nums2, memo), dfs(i, j - 1, nums1, nums2, memo)));
	}
}
