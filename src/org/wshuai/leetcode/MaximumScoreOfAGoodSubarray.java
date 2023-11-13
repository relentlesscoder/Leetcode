package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/2023.
 * #1793 https://leetcode.com/problems/maximum-score-of-a-good-subarray/
 */
public class MaximumScoreOfAGoodSubarray {

	// time O(n), space O(1)
	public int maximumScore(int[] nums, int k) {
		int res = nums[k], min = nums[k], n = nums.length, left = k, right = k;
		while (left > 0 || right < n - 1) {
			if (left == 0) {
				right++;
			} else if (right == n - 1) {
				left--;
			} else if (nums[left - 1] > nums[right + 1]) {
				left--;
			} else {
				right++;
			}
			min = Math.min(min, Math.min(nums[left], nums[right]));
			res = Math.max(res, min * (right - left + 1));
		}
		return res;
	}
}
