package org.wshuai.leetcode;

/**
 * Created by Wei on 7/22/2017.
 * #312 https://leetcode.com/problems/burst-balloons/
 */
public class BurstBalloons {
	//DP
	public int maxCoins(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}

		int n = nums.length;
		int[][] dp = new int[n][n];
		//l is the length of the subrange
		for(int l = 1; l <= n; l++){
			for(int i = 0; i + l <= n; i++){
				int j = i + l - 1;
				for(int k = i; k <= j; k++){
					int coins = nums[k] * (i < 1 ? 1 : nums[i-1]) * (j >= n - 1 ? 1 : nums[j+1]);
					coins += k != i ? dp[i][k-1] : 0;
					coins += k != j ? dp[k + 1][j] : 0;
					dp[i][j] = Math.max(dp[i][j], coins);
				}
			}
		}
		return dp[0][n-1];
	}
}
