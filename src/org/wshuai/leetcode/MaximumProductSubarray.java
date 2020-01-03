package org.wshuai.leetcode;

/**
 * Created by Wei on 11/27/2019.
 * #152 https://leetcode.com/problems/maximum-product-subarray/
 */
public class MaximumProductSubarray {
	public int maxProduct(int[] nums) {
		int N = nums.length;
		int max, local_max, local_min;
		max = local_max = local_min = nums[0];
		for(int i = 1; i < N; i++){
			int p1 = nums[i] * local_max;
			int p2 = nums[i] * local_min;
			local_max = Math.max(nums[i], Math.max(p1, p2));
			local_min = Math.min(nums[i], Math.min(p1, p2));
			max = Math.max(max, local_max);
		}
		return max;
	}
}
