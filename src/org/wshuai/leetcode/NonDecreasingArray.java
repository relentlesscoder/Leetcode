package org.wshuai.leetcode;

/**
 * Created by Wei on 11/06/2019.
 * #0665 https://leetcode.com/problems/non-decreasing-array/
 */
public class NonDecreasingArray {
	// time O(n)
	public boolean checkPossibility(int[] nums) {
		int count = 0;
		for(int i = 1; i < nums.length && count <= 1; i++){
			if(nums[i - 1] > nums[i]){
				count++;
				if(i < 2 || nums[i - 2] <= nums[i]){
					nums[i - 1] = nums[i];
				}else{
					nums[i] = nums[i - 1];
				}
			}
		}
		return count <= 1;
	}
}
