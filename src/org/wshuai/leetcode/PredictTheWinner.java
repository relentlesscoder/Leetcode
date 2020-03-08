package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2019.
 * #0486 https://leetcode.com/problems/predict-the-winner/
 */
public class PredictTheWinner {
	// time O(n^2), space(n^2)
	public boolean predictTheWinner(int[] nums) {
		int n = nums.length;
		int[][] dp = new int[n][n];

		for(int i = 0; i < n; i++){
			dp[i][i] = nums[i];
		}

		for(int len = 1; len < n; len++){
			for(int i = 0; i + len < n; i++){
				dp[i][i + len] = Math.max(nums[i] - dp[i + 1][i + len],
					nums[i + len] - dp[i][i + len - 1]);
			}
		}
		return dp[0][n - 1] >= 0;
	}

	// time O(n^2), space O(n^2)
	public boolean predictTheWinnerMemo(int[] nums) {
		int n = nums.length;
		int[][] dp = new int[n][n];
		return dfs(0, n - 1, nums, dp) >= 0;
	}

	private int dfs(int i, int j, int[] nums, int[][] dp){
		if(i > j){
			return 0;
		}
		if(dp[i][j] != 0){
			return dp[i][j];
		}
		int score = Math.max(nums[i] - dfs(i + 1, j, nums, dp),
			nums[j] - dfs(i, j - 1, nums, dp));
		dp[i][j] = score;
		return score;
	}
}
