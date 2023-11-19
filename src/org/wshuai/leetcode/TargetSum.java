package org.wshuai.leetcode;


/**
 * Created by Wei on 01/25/2017.
 * #0494 https://leetcode.com/problems/target-sum/
 */
public class TargetSum {

	// time O(2*m*n), space O(2*m*n)
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

	// time O(2*m*n), space O(2*m*n)
	public int findTargetSumWays(int[] nums, int S) {
		int n = nums.length, sum = 0;
		for(int num : nums){
			sum += num;
		}
		int m = (sum << 1);
		return dfs(0, nums, sum + S, sum, new Integer[n + 1][m + 1]);
	}

	private int dfs(int start, int[] nums, int target, int cur, Integer[][] dp){
		if(start == nums.length){
			return target == cur ? 1 : 0;
		}
		if(dp[start][cur] != null){
			return dp[start][cur];
		}
		dp[start][cur] = dfs(start + 1, nums, target, cur + nums[start], dp)
			+ dfs(start + 1, nums, target, cur - nums[start], dp);
		return dp[start][cur];
	}
}
