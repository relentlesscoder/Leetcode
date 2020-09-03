package org.wshuai.leetcode;

/**
 * Created by Wei on 09/02/2020.
 * #1567 https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/
 */
public class MaximumLengthOfSubarrayWithPositiveProduct {

	// time O(n)
	public int getMaxLen(int[] nums) {
		int res = 0, prev = -1, first = -1, last = -1, count = 0;
		for(int i = 0; i < nums.length; i++){
			if(nums[i] == 0){
				res = Math.max(res, maxLen(nums, prev + 1, i - 1, first, last, count));
				prev = i;
				first = -1;
				last = -1;
				count = 0;
			}else if(nums[i] < 0){
				first = first == -1 ? i : first;
				last = i;
				count++;
			}
		}
		return Math.max(res, maxLen(nums, prev + 1, nums.length - 1, first, last, count));
	}

	private int maxLen(int[] nums, int i, int j, int first, int last, int count){
		int len = j - i + 1;
		if(len == 0 || count % 2 == 0){
			return len;
		}
		return len - Math.min(first - i + 1, j - last + 1);
	}
}
