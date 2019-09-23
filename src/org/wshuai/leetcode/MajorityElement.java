package org.wshuai.leetcode;

import org.wshuai.algorithm.sorting.QuickSort;

/**
 * Created by Wei on 8/18/2016.
 * #169 https://leetcode.com/problems/majority-element/
 */
public class MajorityElement {
	//O(n)
	public int majorityElement(int[] nums) {
		int count = 1;
		int can = nums[0];
		int len = nums.length;
		for (int i = 1; i < len; i++) {
			if (nums[i] == can) {
				count++;
			} else if (count == 0) {
				can = nums[i];
				count = 1;
			} else {
				count--;
			}
		}
		return can;
	}

	public int majorityElementSort(int[] nums) {
		int len = nums.length;
		QuickSort.quickSort(nums, 0, len - 1);
		return nums[len / 2];
	}
}
