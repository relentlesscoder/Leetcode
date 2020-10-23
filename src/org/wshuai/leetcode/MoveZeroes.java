package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0283 https://leetcode.com/problems/move-zeroes/
 */
public class MoveZeroes {

	// time O(n)
	public void moveZeroes(int[] nums) {
		int n = nums.length;
		for(int i = 0, j = 0; j < n; j++){
			if(nums[j] != 0){
				int temp = nums[i];
				nums[i++] = nums[j];
				nums[j] = temp;
			}
		}
	}
}
