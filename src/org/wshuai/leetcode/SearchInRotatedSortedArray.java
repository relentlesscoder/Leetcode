package org.wshuai.leetcode;

/**
 * Created by Wei on 10/11/2016.
 * #33 https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
public class SearchInRotatedSortedArray {

	//O(lg(n))
	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}

		int i = 0;
		int j = nums.length - 1;
		while (i <= j) {
			int left = nums[i];
			int right = nums[j];
			int m = i + (j - i) / 2;
			int middle = nums[m];
			if (middle == target) {
				return m;
			}
			if (left <= middle) {
				if (middle > target && target >= left) {
					j = m - 1;
				} else {
					i = m + 1;
				}
			} else {
				if (middle < target && target <= right) {
					i = m + 1;
				} else {
					j = m - 1;
				}
			}
		}
		return -1;
	}
}
