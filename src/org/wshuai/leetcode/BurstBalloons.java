package org.wshuai.leetcode;

/**
 * Created by Wei on 07/22/2017.
 * #0312 https://leetcode.com/problems/burst-balloons/
 */
public class BurstBalloons {
	// time O(n^3)
	public int maxCoins(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		int n = nums.length;
		int[][] dp = new int[n][n];
		for(int l = 1; l <= n; l++){
			for(int i = 0; i + l - 1 < n; i++){
				int j = i + l - 1;
				for(int k = i; k <= j; k++){
					// If last balloon to burst within i and j is at k, the coins we earned
					// is nums[i - 1] * nums[k] * nums[j + 1] + dp[i][k - 1] + dp[k + 1][j]
					int coins = nums[k] * (i >= 1 ? nums[i - 1] : 1)
							* (j < n - 1 ? nums[j + 1] : 1);
					coins += k != i ? dp[i][k - 1] : 0;
					coins += k != j ? dp[k + 1][j] : 0;
					dp[i][j] = Math.max(dp[i][j], coins);
				}
			}
		}
		return dp[0][n - 1];
	}
}
