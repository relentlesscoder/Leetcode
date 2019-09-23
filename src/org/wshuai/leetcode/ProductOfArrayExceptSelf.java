package org.wshuai.leetcode;

/**
 * Created by Wei on 10/8/16.
 * #238 https://leetcode.com/problems/product-of-array-except-self/
 */
public class ProductOfArrayExceptSelf {

	//O(1) space
	public int[] productExceptSelf(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		int len = nums.length;
		int[] out = new int[len];
		int p = 1;
		for (int i = 1; i < len; i++) {
			p *= nums[i - 1];
			out[i] = p;
		}
		int m = 1;
		for (int i = len - 2; i >= 0; i--) {
			m *= nums[i + 1];
			out[i] = out[i] * m;
		}
		out[0] = m;
		return out;
	}
}
