package org.wshuai.leetcode;

/**
 * Created by Wei on 8/19/19.
 * #704 https://leetcode.com/problems/binary-search/
 */
public class BinarySearch {
	public int search(int[] nums, int target) {
		int l = 0;
		int r = nums.length - 1;
		while (l <= r) {
			int m = (l + r) / 2;
			if (nums[m] < target) {
				l = m + 1;
			} else if (nums[m] > target) {
				r = m - 1;
			} else {
				return m;
			}
		}
		return -1;
	}
}
