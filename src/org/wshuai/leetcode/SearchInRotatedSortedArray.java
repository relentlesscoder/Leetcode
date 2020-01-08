package org.wshuai.leetcode;

/**
 * Created by Wei on 10/11/2016.
 * #0033 https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
public class SearchInRotatedSortedArray {

	// time O(log(n))
	public int search(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		while(left <= right){
			int mid = left + (right - left) / 2;
			if(nums[mid] == target){
				return mid;
			}

			// turning point is in the right half
			if(nums[mid] > nums[right]){
				// left half is sorted
				if(target < nums[mid] && target >= nums[left]){
					right = mid - 1;
				}else{
					left = mid + 1;
				}
				// turning point is in the left half
			}else if(nums[mid] < nums[left]){
				// right half is sorted
				if(target > nums[mid] && target <= nums[right]){
					left = mid + 1;
				}else{
					right = mid - 1;
				}
			}else{
				if(nums[mid] < target){
					left = mid + 1;
				}else{
					right = mid - 1;
				}
			}
		}
		return -1;
	}
}
