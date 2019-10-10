package org.wshuai.leetcode;

/**
 * Created by Wei on 7/18/17.
 * #540 https://leetcode.com/problems/single-element-in-a-sorted-array/
 */
public class SingleElementInASortedArray {
	public int singleNonDuplicate(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int l = 0;
		int r = nums.length / 2;
		while (l < r) {
			int m = (l + r) / 2;
			if (nums[2 * m] != nums[2 * m + 1]) {
				r = m;
			} else {
				l = m + 1;
			}
		}
		return nums[2 * l];
	}
}
