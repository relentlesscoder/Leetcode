package org.wshuai.leetcode;

/**
 * Created by Wei on 10/17/2016.
 * #0198 https://leetcode.com/problems/house-robber/
 */
public class HouseRobber {
	// time O(n), space O(n)
	public int rob(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		int res = 0, n = nums.length;
		int[] dp = new int[n + 1];
		dp[1] = nums[0];
		for(int i = 2; i <= n; i++){
			dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
		}
		return dp[n];
	}
}
