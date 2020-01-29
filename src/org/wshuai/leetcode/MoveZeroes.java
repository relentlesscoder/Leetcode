package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0283 https://leetcode.com/problems/move-zeroes/
 */
public class MoveZeroes {
	// time O(n)
	public void moveZeroes(int[] nums) {
		if(nums == null || nums.length == 0){
			return;
		}
		int n = nums.length, j = 0;
		for(int i = 0; i < n; i++){
			if(nums[i] != 0){
				nums[j++] = nums[i];
			}
		}
		for(int i = j; i < n; i++){
			nums[i] = 0;
		}
	}
}
