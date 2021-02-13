package org.wshuai.leetcode;

/**
 * Created by Wei on 06/07/2017.
 * #0154 https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 */
public class FindMinimumInRotatedSortedArrayII {

	// time O(log(n)), worst case O(n)
	public int findMin(int[] nums) {
		int left = 0, right = nums.length - 1;
		while(left < right){
			while(left < right && nums[left] == nums[left + 1]){
				left++;
			}
			while(left < right && nums[right] == nums[right - 1]){
				right--;
			}
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
