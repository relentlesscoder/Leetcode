package org.wshuai.leetcode;

/**
 * Created by Wei on 01/20/2020.
 * #0162 https://leetcode.com/problems/find-peak-element/
 */
public class FindPeakElement {

	// time O(log(n)), space O(1)
	public int findPeakElement(int[] nums) {
		int n = nums.length, low = 0, high = n - 1;
		while (low < high) {
			int mid = (high + low) >> 1;
			if (nums[mid] <= nums[mid + 1]) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}
}
