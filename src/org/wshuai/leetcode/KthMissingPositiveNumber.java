package org.wshuai.leetcode;

/**
 * Created by Wei on 08/08/2020.
 * #1539 https://leetcode.com/problems/kth-missing-positive-number/
 */
public class KthMissingPositiveNumber {

	// time O(log(n)), space O(1)
	public int findKthPositive(int[] arr, int k) {
		int low = 0, high = arr.length; // set high to length of the array to deal with the case that missing number is greater than the last value of array
		while (low < high) {
			int mid = (low + high) >> 1;
			if (arr[mid] - (mid + 1) < k) { // missing numbers are less than k, then we need to discard left half and check right half
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low + k;
	}
}
