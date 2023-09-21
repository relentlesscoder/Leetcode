package org.wshuai.leetcode;

/**
 * Created by Wei on 09/21/2023.
 * #1749 https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/
 */
public class MaximumAbsoluteSumOfAnySubarray {

	// time O(n), space O(1)
	public int maxAbsoluteSum(int[] nums) {
		int currentSubarrayMax = 0, currentSubarrayMin = 0, maxSubarraySum = 0;
		for (int i = 0; i < nums.length; i++) {
			currentSubarrayMax = Math.max(currentSubarrayMax + nums[i], nums[i]);
			currentSubarrayMin = Math.min(currentSubarrayMin + nums[i], nums[i]);
			maxSubarraySum = Math.max(maxSubarraySum, Math.max(currentSubarrayMax, -currentSubarrayMin));
		}
		return maxSubarraySum;
	}
}
