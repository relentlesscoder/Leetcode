package org.wshuai.leetcode;

/**
 * Created by Wei on 05/31/2020.
 * #1464 https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/
 */
public class MaximumProductOfTwoElementsInAnArray {

	// time O(n)
	public int maxProduct(int[] nums) {
		int max = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
		for(int a : nums){
			if(a > max){
				second = max;
				max = a;
			}else if(a > second){
				second = a;
			}
		}
		return (max - 1) * (second - 1);
	}
}
