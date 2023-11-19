package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2023.
 * #2161 https://leetcode.com/problems/partition-array-according-to-given-pivot/
 */
public class PartitionArrayAccordingToGivenPivot {

	// time O(n), space O(n)
	public int[] pivotArray(int[] nums, int pivot) {
		int n = nums.length, j = 0, k = n - 1;
		int[] res = new int[n];
		for (int i = 0; i < n; i++) {
			if (nums[i] < pivot) {
				res[j++] = nums[i];
			} else if (nums[i] > pivot) {
				res[k--] = nums[i];
			}
		}
		for (int left = k + 1, right = n - 1; right >= j; left++, right--) {
			if (left < right) {
				int temp = res[left];
				res[left] = res[right];
				res[right] = temp;
			} else if (right < k + 1) {
				res[right] = pivot;
			}
		}
		return res;
	}
}
