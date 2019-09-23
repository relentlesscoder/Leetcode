package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/16.
 * #31 https://leetcode.com/problems/next-permutation/
 */
public class NextPermutation {
	public void nextPermutation(int[] nums) {
		if (nums == null || nums.length <= 1) {
			return;
		}

		int len = nums.length;
		int p = len - 2;
		while (p >= 0 && nums[p] >= nums[p + 1]) {
			p--;
		}
		if (p != -1) {
			int val = nums[p];
			int q = len - 1;
			while (q > p && nums[q] <= val) {
				q--;
			}
			nums[p] = nums[q];
			nums[q] = val;
		}
		int left = p + 1;
		int right = len - 1;
		while (left < right) {
			int temp = nums[left];
			nums[left] = nums[right];
			nums[right] = temp;
			left++;
			right--;
		}
	}
}
