package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #153 https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class FindMinimumInRotatedSortedArray {
	public int findMin(int[] nums) {
		int len = nums.length;
		if (len == 1) {
			return nums[0];
		}
		return fMin(nums, 0, len - 1);
	}

	private int fMin(int[] nums, int i, int j) {
		int x = nums[i];
		int y = nums[j];

		if (i == j) {
			return x;
		}
		if (i == j - 1) {
			return x < y ? x : y;
		}
		if (x < y) {
			return x;
		}
		int m = (i + j) / 2;
		int z = nums[m];

		if (z > x) {
			return fMin(nums, m, j);
		} else {
			return fMin(nums, i, m);
		}
	}
}
