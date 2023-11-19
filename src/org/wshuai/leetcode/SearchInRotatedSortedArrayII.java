package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2016.
 * #0081 https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 */
public class SearchInRotatedSortedArrayII {

	// time O(log(n)), worst case O(n)
	public boolean search(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		while(left <= right){
			while (left < right && nums[left] == nums[left + 1]){
				left++; // skip duplicates from the left
			}
			while (right > left && nums[right] == nums[right - 1]){
				right--; // skip duplicates from the right
			}
			int mid = left + (right - left) / 2;
			if(nums[mid] == target){
				return true;
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
		return false;
	}
}
