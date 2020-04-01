package org.wshuai.leetcode;

/**
 * Created by Wei on 08/19/2019.
 * #0674 https://leetcode.com/problems/longest-continuous-increasing-subsequence/
 */
public class LongestContinuousIncreasingSubsequence {
	// time O(n)
	public int findLengthOfLCIS(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int res = 0, n = nums.length, i = 0, j = 1;
		for (; j < n; j++) {
			if (nums[j] <= nums[j - 1]) {
				res = Math.max(res, j - i);
				i = j;
			}
		}
		return Math.max(res, j - i);
	}
}
