package org.wshuai.leetcode;

/**
 * Created by Wei on 08/26/2016.
 * #0088 https://leetcode.com/problems/merge-sorted-array/
 */
public class MergeSortedArray {

	// O(m+n)
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int i = m - 1, j = n - 1, k = m + n - 1;
		for(; i >= 0 && j >= 0; ){
			nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
		}
		for(; j >= 0; j--, k--){
			nums1[k] = nums2[j];
		}
	}
}
