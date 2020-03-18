package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 07/25/2017.
 * #0628 https://leetcode.com/problems/maximum-product-of-three-numbers/
 */
public class MaximumProductOfThreeNumbers {
	// time O(n)
	public int maximumProduct(int[] nums) {
		int n = nums.length;
		int[] arr = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
		for (int num : nums) {
			if (num < arr[0]) {
				arr[1] = arr[0];
				arr[0] = num;
			} else if (num < arr[1]) {
				arr[1] = num;
			}
			if (num > arr[4]) {
				arr[2] = arr[3];
				arr[3] = arr[4];
				arr[4] = num;
			} else if (num > arr[3]) {
				arr[2] = arr[3];
				arr[3] = num;
			} else if (num > arr[2]) {
				arr[2] = num;
			}
		}
		return Math.max(arr[0] * arr[1] * arr[4], arr[2] * arr[3] * arr[4]);
	}

	// time O(n*log(n))
	public int maximumProductSort(int[] nums) {
		int n = nums.length;
		Arrays.sort(nums);
		return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 1] * nums[n - 2] * nums[n - 3]);
	}
}
