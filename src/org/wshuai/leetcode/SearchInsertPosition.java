package org.wshuai.leetcode;

/**
 * Created by Wei on 01/07/2020.
 * #0035 https://leetcode.com/problems/search-insert-position/
 */
public class SearchInsertPosition {

	// time O(log(n)), space O(1)
	public int searchInsert(int[] nums, int target) {
		int low = 0, high = nums.length;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] >= target) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}
}
