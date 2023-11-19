package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0153 https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class FindMinimumInRotatedSortedArray {

	// time O(log(n))
	public int findMin(int[] nums) {
		int left = 0, right = nums.length - 1;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(nums[mid] > nums[right]){ // turning point is on right - [6, 7, 1, 2, 3]
				left = mid + 1;
			}else{
				right = mid; // turning point is on left - [3, 4, 5, 1, 2]
			}
		}
		return nums[left];
	}
}
