package org.wshuai.leetcode;

/**
 * Created by Wei on 11/06/2023.
 * #1874 https://leetcode.com/problems/minimize-product-sum-of-two-arrays/
 */
public class MinimizeProductSumOfTwoArrays {

	// time O(n), space O(1)
	public int minProductSum(int[] nums1, int[] nums2) {
		// proof: https://leetcode.com/problems/minimize-product-sum-of-two-arrays/editorial/
		int res = 0, n = nums1.length;
		int[] arr1 = new int[101], arr2 = new int[101];
		for (int i = 0; i < n; i++) {
			arr1[nums1[i]]++;
			arr2[nums2[i]]++;
		}
		for (int i = 1, j = 100; i <= 100 && j >= 1; ) {
			if (arr1[i] == 0) {
				i++;
			} else if (arr2[j] == 0) {
				j--;
			} else {
				res += i * j;
				arr1[i]--;
				arr2[j]--;
			}
		}
		return res;
	}
}
