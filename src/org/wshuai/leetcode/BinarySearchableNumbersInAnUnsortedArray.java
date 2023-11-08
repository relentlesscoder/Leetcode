package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2023.
 * #1966 https://leetcode.com/problems/binary-searchable-numbers-in-an-unsorted-array/
 */
public class BinarySearchableNumbersInAnUnsortedArray {

	// time O(n), space O(n)
	public int binarySearchableNumbers(int[] nums) {
		int res = 0, n = nums.length, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		boolean[] searchable = new boolean[n];
		for (int i = 0; i < n; i++) { // for a value to be searchable, it should be strictly greater than all elements at its left and smaller than all elements at its right
			if (nums[i] > max) {
				searchable[i] = true;
				max = nums[i];
			}
		}
		for (int i = n - 1; i >= 0; i--) {
			if (nums[i] < min) {
				min = nums[i];
				res += (searchable[i] ? 1 : 0);
			}
		}
		return res;
	}
}
