package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/28/2016.
 * #0377 https://leetcode.com/problems/combination-sum-iv/
 */
public class CombinationSumIV {
	// time O(n^2)
	// http://www.cnblogs.com/grandyang/p/5705750.html
	public int combinationSum4(int[] nums, int target) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		int[] dp = new int[target + 1];
		dp[0] = 1;
		Arrays.sort(nums);
		for(int i = 1; i <= target; i++){
			for(int j = 0; j < nums.length; j++){
				if(nums[j] > i){
					break;
				}
				dp[i] += dp[i - nums[j]];
			}
		}
		return dp[target];
	}

	private int[] dp;

	public int combinationSum4RecursionWithMemo(int[] nums, int target) {
		if(nums.length == 0){
			return 0;
		}
		dp = new int[target + 1];
		Arrays.fill(dp, -1);
		return dfs(nums, target, dp);
	}

	private int dfs(int[] nums, int target, int[] dp){
		if(target <= 0){
			return target == 0 ? 1 : 0;
		}
		if(dp[target] != -1){
			return dp[target];
		}
		int res = 0;
		for(int i = 0; i < nums.length; i++){
			res += dfs(nums, target - nums[i], dp);
		}
		dp[target] = res;
		return res;
	}
}
