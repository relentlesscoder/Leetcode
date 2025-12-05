package org.wshuai.leetcode;

/**
 * Created by Wei on 08/26/2016.
 * #0088 https://leetcode.com/problems/merge-sorted-array/
 */
public class MergeSortedArray {

    // time O(m + n), space O(1)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n - 1, j = n - 1;
        for (int i = m - 1; i >= 0 && j >= 0; ) {
            nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
        for (; j >= 0; j--) {
            // if nums2 still has x number left, then nums1 has x greater numbers picked thus just override it
            nums1[k--] = nums2[j];
        }
    }
}
