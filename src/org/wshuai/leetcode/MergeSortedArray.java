package org.wshuai.leetcode;

/**
 * Created by Wei on 08/26/2016.
 * #0088 https://leetcode.com/problems/merge-sorted-array/
 */
public class MergeSortedArray {

	// time O(m + n)
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int j =  n - 1, k = m + n - 1;
		for(int i = m - 1; i >= 0 && j >= 0; ){
			nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
		}
		// j >= 0 means nums1 is done
		for(; j >= 0; j--){
			nums1[k--] = nums2[j];
		}
		// i >= 0 means nums2 is done, however we do not need to handle
		// it since nums1 is already sorted
	}
}
