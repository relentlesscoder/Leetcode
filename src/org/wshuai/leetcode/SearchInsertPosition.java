package org.wshuai.leetcode;

/**
 * Created by Wei on 01/07/2020.
 * #0035 https://leetcode.com/problems/search-insert-position/
 */
public class SearchInsertPosition {
	// time O(log(n))
	public int searchInsert(int[] nums, int target) {
		int left = 0;
		int right = nums.length;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(nums[mid] < target){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}
}
