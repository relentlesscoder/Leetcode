package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2016.
 * #0031 https://leetcode.com/problems/next-permutation/
 */
public class NextPermutation {

	// time O(n), space O(1)
	public void nextPermutation(int[] nums) {
		// Next permutation is actually the least permutation that are greater than
		// the current. If the number array is in descending order like [5,4,3,2,1]
		// then we can't construct a permutation larger than it. To find the next
		// permutation:
		// 	 1. From the end of the array, find the first index i that satisfy the
		//      condition nums[i] < nums[i + 1], now we know that subarray [i + 1, n - 1]
		//      after this index i is in descending order. We choose i since this is the
		//      least significant digits to replace (to make the result smallest).
		//        [5,4,8,9,3,5,5,1,4,2] -> index i is 7
		//   2. From the end of the array, find the first number nums[j] > nums[i] and
		//      swap them. This actually finds the least number from right to replace
		//      nums[i]. Note that the after swap the subarray [i + 1, n - 1] maintains
		//      the descending order. We choose j since this is the least digits we can
		//      use to replace i (to make the result smallest).
		//        [5,4,8,9,3,5,5,1,4,2] -> [5,4,8,9,3,5,5,2,4,1]
		//   3. Reverse the subarray [i + 1, n - 1] to sort it in ascending order (to
		//      make the result smallest).
		//        [5,4,8,9,3,5,5,2,4,1] -> [5,4,8,9,3,5,5,2,1,4]
		int n = nums.length, left = n - 2;
		while (left >= 0 && nums[left] >= nums[left + 1]) {
			left--;
		}
		if (left != -1) {
			int right = n - 1, pivot = nums[left];
			while (right > left && nums[right] <= pivot) {
				right--;
			}
			nums[left] = nums[right];
			nums[right] = pivot;
		}
		for (int start = left + 1, end = n - 1; start < end; start++, end--) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
		}
	}
}
