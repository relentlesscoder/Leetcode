package org.wshuai.leetcode;

/**
 * Created by Wei on 07/25/2017.
 * #0645 https://leetcode.com/problems/set-mismatch/
 */
public class SetMismatch {
	// time O(n), space O(1)
	// bucket sort
	public int[] findErrorNums(int[] nums) {
		int n = nums.length;
		for(int i = 0; i < n; i++){
			while(nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]){
				int temp = nums[nums[i] - 1];
				nums[nums[i] - 1] = nums[i];
				nums[i] = temp;
			}
		}
		for(int i = 0; i < n; i++){
			if(nums[i] != i + 1){
				return new int[]{nums[i], i + 1};
			}
		}
		return new int[0];
	}
}
