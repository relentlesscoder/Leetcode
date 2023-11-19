package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2016.
 * #0238 https://leetcode.com/problems/product-of-array-except-self/
 */
public class ProductOfArrayExceptSelf {

	// time O(n), space O(1)
	public int[] productExceptSelf(int[] nums) {
		int n = nums.length, right = 1;
		int[] res = new int[n];
		res[0] = 1;
		for(int i = 1; i < n; i++){
			res[i] = res[i - 1] * nums[i - 1];
		}
		for(int i = n - 1; i >= 0; i--){
			res[i] *= right;
			right *= nums[i];
		}
		return res;
	}
}
