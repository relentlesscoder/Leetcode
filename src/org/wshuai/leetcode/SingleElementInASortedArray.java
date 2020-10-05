package org.wshuai.leetcode;

/**
 * Created by Wei on 07/18/2017.
 * #0540 https://leetcode.com/problems/single-element-in-a-sorted-array/
 */
public class SingleElementInASortedArray {

	// time O(n*log(n))
	public int singleNonDuplicate(int[] nums) {
		int n = nums.length, low = 0, high = n/2;
		while(low < high){
			int mid = low + (high - low) / 2, index = mid << 1;
			if(nums[index] == nums[index + 1]){
				low = mid + 1;
			}else{
				high = mid;
			}
		}
		return nums[low << 1];
	}

	/*
			3 3 7 7 10 11 11
			0 1 2 3 4  5  6

			0 1 2

			0 -> 3
		*/
}
