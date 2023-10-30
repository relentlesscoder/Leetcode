package org.wshuai.leetcode;

/**
 * Created by Wei on 10/29/2023.
 * #2717 https://leetcode.com/problems/semi-ordered-permutation/
 */
public class SemiOrderedPermutation {

	// time O(n), space O(1)
	public int semiOrderedPermutation(int[] nums) {
		int res = 0, n = nums.length, first = 0, last = n - 1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 1) {
				first = i;
			} else if (nums[i] == n) {
				last = i;
			}
		}
		if (first > last) { // move first to be at the front of the last
			res += first - last;
			first = last;
			last = first + 1;
		}
		return res + n - 1 - last + first;
	}
}
