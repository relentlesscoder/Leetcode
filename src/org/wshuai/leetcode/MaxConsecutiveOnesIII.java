package org.wshuai.leetcode;

/**
 * Created by Wei on 09/12/2019.
 * #1004 https://leetcode.com/problems/max-consecutive-ones-iii/
 */
public class MaxConsecutiveOnesIII {

	// time O(n), space O(1)
	public int longestOnes(int[] nums, int k) {
		int res = 0, zeros = 0;
		for (int i = 0, j = 0; i < nums.length; i++) {
			zeros += 1 - nums[i];
			while (zeros > k) {
				zeros -= 1 - nums[j++];
			}
			res = Math.max(res, i - j + 1);
		}
		return res;
	}
}
