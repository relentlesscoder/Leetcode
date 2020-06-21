package org.wshuai.leetcode;

/**
 * Created by Wei on 09/05/2019.
 * #0724 https://leetcode.com/problems/find-pivot-index/
 */
public class FindPivotIndex {
	// time O(n), space O(n)
	public int pivotIndex(int[] nums) {
		if(nums == null || nums.length == 0){
			return -1;
		}
		int n = nums.length, sum = 0;
		int[] left = new int[n];
		for(int i = 0; i < n; i++){
			left[i] += sum;
			sum += nums[i];
		}
		for(int i = 0; i < n; i++){
			if(sum - nums[i] == (left[i] << 1)){
				return i;
			}
		}
		return -1;
	}
}
