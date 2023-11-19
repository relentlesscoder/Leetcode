package org.wshuai.leetcode;

/**
 * Created by Wei on 10/30/2023.
 * #2540 https://leetcode.com/problems/minimum-common-value/
 */
public class MinimumCommonValue {

	// time O(m + n), space O(1)
	public int getCommon(int[] nums1, int[] nums2) {
		int res = 0;
		for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
			if (nums1[i] < nums2[j]) {
				i++;
			} else if (nums1[i] > nums2[j]) {
				j++;
			} else {
				return nums1[i];
			}
		}
		return -1;
	}
}
