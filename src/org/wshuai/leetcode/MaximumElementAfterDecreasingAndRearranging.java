package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/21/2023.
 * #1846 https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging/
 */
public class MaximumElementAfterDecreasingAndRearranging {

	// time O(n * log(n)), space O(1)
	public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
		Arrays.sort(arr);
		int res = 1, pre = 1;
		for (int i = 1; i < arr.length; i++) {
			pre = Math.min(arr[i], pre + 1);
			res = Math.max(res, pre);
		}
		return res;
	}
}
