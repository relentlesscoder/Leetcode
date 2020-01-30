package org.wshuai.leetcode;

/**
 * Created by Wei on 11/14/2016.
 * #0300 https://leetcode.com/problems/longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence {
    // time O(n^2)
	public int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int n = nums.length, res = 1;
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			res = Math.max(res, dp[i]);
		}
		return res;
	}
}
