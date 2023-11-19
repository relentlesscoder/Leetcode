package org.wshuai.leetcode;

/**
 * Created by Wei on 01/20/2020.
 * #0162 https://leetcode.com/problems/find-peak-element/
 */
public class FindPeakElement {

	// time O(log(n)), space O(1)
	public int findPeakElement(int[] nums) {
		// proof for the binary search - https://leetcode.com/problems/find-peak-element/editorial/
		int n = nums.length, low = 0, high = n - 1; // set high to n - 1 to avoid index overflow
		while (low < high) {
			int mid = (low + high) >> 1;
			if (nums[mid] <= nums[mid + 1]) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}
}
