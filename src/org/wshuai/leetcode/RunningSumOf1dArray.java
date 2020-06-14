package org.wshuai.leetcode;

/**
 * Created by Wei on 06/14/2020.
 * #5453 https://leetcode.com/problems/running-sum-of-1d-array/
 */
public class RunningSumOf1dArray {

	// time O(n)
	public int[] runningSum(int[] nums) {
		int n = nums.length;
		int[] res = new int[n];
		res[0] = nums[0];
		for(int i = 1; i < n; i++){
			res[i] = res[i - 1] + nums[i];
		}
		return res;
	}
}
