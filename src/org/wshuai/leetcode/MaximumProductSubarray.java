package org.wshuai.leetcode;

/**
 * Created by Wei on 11/27/2019.
 * #0152 https://leetcode.com/problems/maximum-product-subarray/
 */
public class MaximumProductSubarray {
	// time O(n)
	public int maxProduct(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		int res = nums[0], max = nums[0], min = nums[0];
		for(int i = 1; i < nums.length; i++){
			int p1 = nums[i] * max;
			int p2 = nums[i] * min;
			max = Math.max(nums[i], Math.max(p1, p2));
			min = Math.min(nums[i], Math.min(p1, p2));
			res = Math.max(res, max);
		}
		return res;
	}
}
