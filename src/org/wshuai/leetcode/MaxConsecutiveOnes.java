package org.wshuai.leetcode;

/**
 * Created by Wei on 02/15/2017.
 * #0485 https://leetcode.com/problems/max-consecutive-ones/
 */
public class MaxConsecutiveOnes {
	// time O(n)
	public int findMaxConsecutiveOnes(int[] nums) {
		int res = 0, n = nums.length, cur = 0;
		for (int i = 0; i < n; i++) {
			if (nums[i] == 1) {
				if (i == 0 || nums[i - 1] == 0) {
					cur = 1;
				} else {
					cur++;
				}
				res = Math.max(res, cur);
			}
		}
		return res;
	}
}
