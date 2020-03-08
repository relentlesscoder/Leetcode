package org.wshuai.leetcode;

/**
 * Created by Wei on 10/30/2016.
 * #0213 https://leetcode.com/problems/house-robber-ii/
 */
public class HouseRobberII {
	// time O(n), space O(n)
	public int rob(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		if(nums.length == 1){
			return nums[0];
		}
		return Math.max(helper(nums, 0, nums.length - 2),
			helper(nums, 1, nums.length - 1));
	}

	private int helper(int[] nums, int i, int j){
		int[] dp = new int[nums.length + 1];
		dp[i] = 0;
		dp[i + 1] = nums[i];
		for(int k = i + 2; k <= j + 1; k++){
			dp[k] = Math.max(dp[k - 1], dp[k - 2] + nums[k - 1]);
		}
		return dp[j + 1];
	}
}
