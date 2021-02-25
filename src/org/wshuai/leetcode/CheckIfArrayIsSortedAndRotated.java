package org.wshuai.leetcode;

/**
 * Created by Wei on 02/24/2021.
 * #1752 https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/
 */
public class CheckIfArrayIsSortedAndRotated {

	// time O(n)
	public boolean check(int[] nums) {
		int i = 0, n = nums.length;
		for(; i < n - 1 && nums[i] <= nums[i + 1]; i++){
		}
		if(i == n - 1){
			return true;
		}
		for(int j = i + 1; j < n - 1; j++){
			if(nums[j] <= nums[j + 1]
				&& nums[j] <= nums[0] && nums[j + 1] <= nums[0]){
				continue;
			}else{
				return false;
			}
		}
		return nums[n - 1] <= nums[0];
	}
}
