package org.wshuai.leetcode;

/**
 * Created by Wei on 12/08/2020.
 * #1664 https://leetcode.com/problems/ways-to-make-a-fair-array/
 */
public class WaysToMakeAFairArray {

	// time O(n)
	public int waysToMakeFair(int[] nums) {
		int res = 0, n = nums.length;
		int[] right = new int[2], left = new int[2];
		for(int i = 0; i < n; i++){
			right[i % 2] += nums[i];
		}
		for(int i = 0; i < n; i++){
			right[i % 2] -= nums[i];
			// after remove index i, even/odd index becomes
			// odd/even on the right side
			if(left[0] + right[1] == left[1] + right[0]){
				res++;
			}
			left[i % 2] += nums[i];
		}
		return res;
	}
}
