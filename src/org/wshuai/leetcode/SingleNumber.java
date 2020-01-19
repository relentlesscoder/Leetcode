package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2016.
 * #0136 https://leetcode.com/problems/single-number/
 */
public class SingleNumber {
	// time O(n), space O(1)
	public int singleNumber(int[] nums) {
		int res = nums[0];
		// x ^ x = 0
		for(int i = 1; i < nums.length; i++){
			res ^= nums[i];
		}
		return res;
	}
}
