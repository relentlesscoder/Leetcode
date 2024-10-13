package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0283 https://leetcode.com/problems/move-zeroes/
 */
public class MoveZeroes {

	// time O(n), space O(1)
	public void moveZeroes(int[] nums) {
		for (int i = 0, j = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j++] = temp;
			}
		}
	}
}
