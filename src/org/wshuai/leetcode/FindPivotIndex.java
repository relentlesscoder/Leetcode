package org.wshuai.leetcode;

/**
 * Created by Wei on 09/05/2019.
 * #0724 https://leetcode.com/problems/find-pivot-index/
 */
public class FindPivotIndex {

	// time O(n), space O(n)
	public int pivotIndex(int[] nums) {
		int n = nums.length, sum = 0, left = 0;
		for(int num : nums){
			sum += num;
		}
		for(int i = 0; i < n; i++){
			if(sum - nums[i] == left + left){
				return i;
			}
			left += nums[i];
		}
		return -1;
	}
}
