package org.wshuai.leetcode;


/**
 * Created by Wei on 01/25/2017.
 * #0494 https://leetcode.com/problems/target-sum/
 */
public class TargetSum {

	// time O(n*sum*2), space O(n*sum*2)
	public int findTargetSumWaysDP(int[] nums, int S) {
		int sum = 0, n = nums.length;
		for(int num : nums){
			sum += num;
		}
		if(S < -sum || S > sum){
			return 0;
		}
		int m = sum << 1;
		int[][] dp = new int[n + 1][m + 1];
		dp[0][sum] = 1;
		for(int i = 1; i <= n; i++){
			for(int j = 0; j <= m; j++){
				if(j >= nums[i - 1]){
					dp[i][j] += dp[i - 1][j - nums[i - 1]];
				}
				if(j + nums[i - 1] <= m){
					dp[i][j] += dp[i - 1][j + nums[i - 1]];
				}
			}
		}
		return dp[n][S + sum];
	}

	// time O(2^n)
	public int findTargetSumWaysMemorization(int[] nums, int S) {
		int sum = 0;
		for(int num : nums){
			sum += num;
		}
		Integer[][] dp = new Integer[nums.length][2 * sum + 1];
		return dfs(0, nums, sum, S + sum, dp);
	}

	private int dfs(int start, int[] nums, int cur, int target, Integer[][] dp){
		if(start == nums.length){
			return cur == target ? 1 : 0;
		}
		if(dp[start][cur] != null){
			return dp[start][cur];
		}
		dp[start][cur] = dfs(start + 1, nums, cur - nums[start], target, dp)
			+ dfs(start + 1, nums, cur + nums[start], target, dp);
		return dp[start][cur];
	}
}
