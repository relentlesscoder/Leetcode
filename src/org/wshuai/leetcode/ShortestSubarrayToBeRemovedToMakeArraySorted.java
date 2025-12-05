package org.wshuai.leetcode;

/**
 * Created by Wei on 09/07/2020.
 * #1574 https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/
 */
public class ShortestSubarrayToBeRemovedToMakeArraySorted {

	// time O(n), space O(1)
	public int findLengthOfShortestSubarray(int[] arr) {
		int n = arr.length, right = n - 1;
		// Find the left end for the non-decreasing suffix of the array
		while (right > 0 && arr[right - 1] <= arr[right]) {
			right--;
		}
		// If right is 0 then array is already non-decreasing
		if (right == 0) {
			return 0;
		}
		int res = right; // Remove all numbers in [0, right - 1]
		for (int left = 0; left == 0 || arr[left - 1] <= arr[left]; left++) {
			// Now keep moving left pointer while prefix [0, left] is non-decreasing,
			// For each left, find next right that satisfy arr[right] >= arr[left]
			// so we can "connect" the prefix and suffix together to form a
			// non-decreasing sequence. Therefore, numbers in range [left + 1,
			// right - 1] needs to be removed for the current left and right pairs
			// so the answer is the minimum among them.
			while (right < n && arr[right] < arr[left]) {
				right++;
			}
			res = Math.min(res, right - left - 1);
		}
		return res;
	}
}
