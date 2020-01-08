package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0153 https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class FindMinimumInRotatedSortedArray {
	// time O(log(n))
	public int findMin(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(nums[mid] < nums[right]){
				right = mid;
			}else{
				left = mid + 1;
			}
		}
		return nums[left];
	}
}
