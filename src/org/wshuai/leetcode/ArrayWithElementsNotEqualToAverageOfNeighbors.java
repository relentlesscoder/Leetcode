package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/25/2023.
 * #1968 https://leetcode.com/problems/array-with-elements-not-equal-to-average-of-neighbors/
 */
public class ArrayWithElementsNotEqualToAverageOfNeighbors {

	// time O(n * log(n)), space O(n)
	public int[] rearrangeArray(int[] nums) {
		Arrays.sort(nums);
		int n = nums.length;
		for (int i = 1; i < n; i += 2) {
			int temp = nums[i];
			nums[i] = nums[i - 1];
			nums[i - 1] = temp;
		}
		return nums;
	}

	// time O(n * log(n)), space O(n)
	public int[] rearrangeArrayWiggleArray(int[] nums) {
		Arrays.sort(nums);
		int n = nums.length, i = 0, j = 0;
		int[] res = new int[n];
		for (; i < n / 2; i++) {
			res[j++] = nums[i];
			res[j++] = nums[i + n / 2 + n % 2];
		}
		if (n % 2 == 1) {
			res[n - 1] = nums[n / 2];
		}
		return res;
	}
}
