package org.wshuai.leetcode;

/**
 * Created by Wei on 02/15/2017.
 * #0485 https://leetcode.com/problems/max-consecutive-ones/
 */
public class MaxConsecutiveOnes {

	// time O(n), space O(1)
	public int findMaxConsecutiveOnes(int[] nums) {
		int res = 0, ones = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				ones = 0;
			} else {
				res = Math.max(res, ++ones);
			}
		}
		return res;
	}

}
