package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2023.
 * #2348 https://leetcode.com/problems/number-of-zero-filled-subarrays/
 */
public class NumberOfZeroFilledSubarrays {

	// time O(n), space O(1)
	public long zeroFilledSubarray(int[] nums) {
		long res = 0, count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				count++;
				res += count; // add zero filled subarrays end at i
			} else {
				count = 0; // reset count
			}
		}
		return res;
	}
}
