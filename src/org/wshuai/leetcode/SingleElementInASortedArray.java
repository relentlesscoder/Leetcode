package org.wshuai.leetcode;

/**
 * Created by Wei on 07/18/2017.
 * #0540 https://leetcode.com/problems/single-element-in-a-sorted-array/
 */
public class SingleElementInASortedArray {
	// time O(n*log(n))
	public int singleNonDuplicate(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		/*

			3 3 7 7 10 11 11
			0 1 2 3 4  5  6

			0 1 2

			0 -> 3
		*/
		int left = 0, right = nums.length / 2;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(nums[2 * mid] != nums[2 * mid + 1]){
				right = mid;
			}else{
				left = mid + 1;
			}
		}
		return nums[2 * left];
	}
}
