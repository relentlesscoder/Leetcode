package org.wshuai.leetcode;

/**
 * Created by Wei on 01/07/2020.
 * #0034 https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

	// time O(log(n))
	public int[] searchRange(int[] nums, int target) {
		if(nums.length == 0){
			return new int[]{-1, -1};
		}
		return new int[]{searchLeft(nums, target),
			searchRight(nums, target)};
	}

	private int searchLeft(int[] nums, int target){
		int left = 0, right = nums.length - 1;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(nums[mid] < target){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return nums[left] == target ? left : -1;
	}

	private int searchRight(int[] nums, int target){
		int left = 0, right = nums.length - 1;
		while(left < right){
			int mid = left + (right - left + 1) / 2;
			if(nums[mid] > target){
				right = mid - 1;
			}else{
				left = mid;
			}
		}
		return nums[left] == target ? left : -1;
	}
}
