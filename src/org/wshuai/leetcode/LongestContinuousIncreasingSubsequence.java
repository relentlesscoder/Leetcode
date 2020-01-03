package org.wshuai.leetcode;

/**
 * Created by Wei on 8/19/19.
 * #674 https://leetcode.com/problems/longest-continuous-increasing-subsequence/
 */
public class LongestContinuousIncreasingSubsequence {
	public int findLengthOfLCIS(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		if(nums.length == 1){
			return 1;
		}
		int max = 0;
		int start = 0;
		int N = nums.length;
		for(int i = 1; i < N; i++){
			if(nums[i - 1] >= nums[i]){
				start = i;
			}
			max = Math.max(i - start + 1, max);
		}
		/*
		start = N - 1;
		for(int i = N - 2; i >= 0; i--){
			if(nums[i + 1] >= nums[i]){
				start = i;
			}
			max = Math.max(start - i + 1, max);
		}
		*/
		return max;
	}
}
