package org.wshuai.leetcode;

/**
 * Created by Wei on 08/19/2019.
 * #0704 https://leetcode.com/problems/binary-search/
 */
public class BinarySearch {
	// time O(log(n))
	public int search(int[] nums, int target) {
		int low = 0, high = nums.length - 1;
		while(low < high){
			int mid = low + ((high - low) >> 1);
			if(nums[mid] < target){
				low = mid + 1;
			}else{
				high = mid;
			}
		}
		return nums[low] == target ? low : -1;
	}
}
