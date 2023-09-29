package org.wshuai.leetcode;

/**
 * Created by Wei on 09/29/2023.
 * #2760 https://leetcode.com/problems/longest-even-odd-subarray-with-threshold/
 */
public class LongestEvenOddSubarrayWithThreshold {

	// time O(n), space O(1)
	public int longestAlternatingSubarray(int[] nums, int threshold) {
		int res = 0, n = nums.length;
		for (int i = 0, j = 0; i < n; i++) {
			if (nums[i] % 2 == 0 && nums[i] <= threshold) {
				j = i;
				int mod = 0;
				while (j < n && nums[j] <= threshold && nums[j] % 2 == mod) {
					mod = 1 - mod;
					j++;
				}
				res = Math.max(res, j - i);
				i = j - 1;
			}
		}
		return res;
	}
}
