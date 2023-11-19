package org.wshuai.leetcode;

/**
 * Created by Wei on 10/29/2023.
 * #2784 https://leetcode.com/problems/check-if-array-is-good/
 */
public class CheckIfArrayIsGood {

	// time O(n), space O(n)
	public boolean isGood(int[] nums) {
		int n = nums.length - 1;
		int[] counter = new int[n + 1];
		for (int num : nums) {
			if (num <= 0 || num > n) {
				return false;
			}
			int limit = num == n ? 2 : 1;
			if (++counter[num] > limit) {
				return false;
			}
		}
		return true;
	}
}
