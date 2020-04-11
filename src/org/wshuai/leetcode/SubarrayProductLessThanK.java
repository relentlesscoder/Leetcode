package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2019.
 * #0713 https://leetcode.com/problems/subarray-product-less-than-k/
 */
public class SubarrayProductLessThanK {

	// time O(n)
	public int numSubarrayProductLessThanK(int[] nums, int k) {
		int res = 0, n = nums.length, cur = 1;
		for(int i = 0, j = 0; j < n; j++){
			cur *= nums[j];
			while(i <= j && cur >= k){
				cur /= nums[i++];
			}
			if(cur < k){
				res += j - i + 1;
			}
		}
		return res;
	}

}
