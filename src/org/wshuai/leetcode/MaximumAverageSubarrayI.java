package org.wshuai.leetcode;

/**
 * Created by Wei on 07/28/2017.
 * #0645 https://leetcode.com/problems/maximum-average-subarray-i/
 */
public class MaximumAverageSubarrayI {
	// time O(n)
	public double findMaxAverage(int[] nums, int k) {
		// https://stackoverflow.com/questions/3884793/why-is-double-min-value-in-not-negative
		double res = -Double.MAX_VALUE, sum = 0.0;
		for(int i = 0; i < nums.length; i++){
			if(i >= k){
				res = Math.max(res, sum / k);
				sum -= nums[i - k];
			}
			sum += nums[i];
		}
		res = Math.max(res, sum / k);
		return res;
	}
}
