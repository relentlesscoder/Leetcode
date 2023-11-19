package org.wshuai.leetcode;

/**
 * Created by Wei on 10/05/2016.
 * #0055 https://leetcode.com/problems/jump-game/
 */
public class JumpGame {

	// time O(n)
	public boolean canJump(int[] nums) {
		int n = nums.length;
		int max = 0;
		for(int i = 0; i < n; i++){
			// nums i denotes the reachable range from i
			if(i > max){
				return false;
			}
			max = Math.max(max, i + nums[i]);
		}
		return true;
	}
}
