package org.wshuai.leetcode;

/**
 * Created by Wei on 01/20/2020.
 * #0162 https://leetcode.com/problems/find-peak-element/
 */
public class FindPeakElement {
	// time O(log(n))
	public int findPeakElement(int[] nums) {
		int left = 0, right = nums.length - 1;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(nums[mid] < nums[mid + 1]){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}
}
