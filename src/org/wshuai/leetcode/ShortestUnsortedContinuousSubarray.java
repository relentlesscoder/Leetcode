package org.wshuai.leetcode;

/**
 * Created by Wei on 07/28/2017.
 * #0581 https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 */
public class ShortestUnsortedContinuousSubarray {
	// time O(n)
	public int findUnsortedSubarray(int[] nums) {
		if(nums == null || nums.length <= 1){
			return 0;
		}
		int max = Integer.MIN_VALUE, end = -2;
		for(int i = 0; i < nums.length; i++){
			max = Math.max(max, nums[i]);
			if(nums[i] < max){
				end = i;
			}
		}
		int min = Integer.MAX_VALUE, begin = -1;
		for(int i = nums.length - 1; i >= 0; i--){
			min = Math.min(min, nums[i]);
			if(nums[i] > min){
				begin = i;
			}
		}
		return end - begin + 1;
	}
}
