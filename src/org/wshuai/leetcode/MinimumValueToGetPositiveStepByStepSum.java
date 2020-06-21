package org.wshuai.leetcode;

/**
 * Created by Wei on 04/19/2020.
 * #1413 https://leetcode.com/problems/minimum-value-to-get-positive-step-by-step-sum/
 */
public class MinimumValueToGetPositiveStepByStepSum {
	// time O(n)
	public int minStartValue(int[] nums) {
		int res = 1, sum = 0;
		for(int i = 0; i < nums.length; i++){
			sum += nums[i];
			if(sum < 1){
				res = Math.max(1 - sum, res);
			}
		}
		return res == Integer.MAX_VALUE ? 1 : res;
	}
}
