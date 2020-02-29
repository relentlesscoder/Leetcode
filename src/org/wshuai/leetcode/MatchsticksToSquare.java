package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/27/2019.
 * #0473 https://leetcode.com/problems/matchsticks-to-square/
 */
public class MatchsticksToSquare {
	// time O(3^n)
	public boolean makesquare(int[] nums) {
		if(nums == null || nums.length < 4){
			return false;
		}
		int sum = 0, target = 0;
		for(int num : nums){
			sum += num;
		}
		if(sum % 4 != 0){
			return false;
		}
		target = sum / 4;
		Arrays.sort(nums);
		return dfs(0, 0, 0, 4, target, nums);
	}

	private boolean dfs(int start, int sum, int used, int count, int target, int[] nums){
		if(count == 1){
			return true;
		}
		if(sum == target){
			return dfs(0, 0, used, count - 1, target, nums);
		}
		for(int i = start; i < nums.length; i++){
			if(((1 << i) & used) > 0){
				continue;
			}
			// recursion tree pruning
			if(sum + nums[i] > target){
				return false;
			}
			if(dfs(i, sum + nums[i], (1 << i) | used, count, target, nums)){
				return true;
			}
		}
		return false;
	}
}
