package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/06/2023.
 * #1877 https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/
 */
public class MinimizeMaximumPairSumInArray {

	// time O(n * log(n)), space O(1)
	public int minPairSum(int[] nums) {
		int res = 0, n = nums.length;
		Arrays.sort(nums);
		for (int i = 0; i < n / 2; i++) {
			res = Math.max(res, nums[i] + nums[n - 1 - i]);
		}
		return res;
	}

}
