package org.wshuai.leetcode;

/**
 * Created by Wei on 8/8/19.
 * #852 https://leetcode.com/problems/peak-index-in-a-mountain-array/
 */
public class PeakIndexInAMountainArray {
	public int peakIndexInMountainArray(int[] A) {
		int left = 0;
		int right = A.length - 1;
		while (left < right) {
			int mid = (left + right) / 2;
			if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
				return mid;
			} else if (A[mid] > A[mid - 1] && A[mid] < A[mid + 1]) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return (A[left] > A[left - 1] && A[left] > A[left + 1]) ? left : -1;
	}
}
