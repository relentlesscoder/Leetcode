package org.wshuai.leetcode;

/**
 * Created by Wei on 01/26/2020.
 * #1335 https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
 */
public class MinimumDifficultyOfAJobSchedule {
	// time O(d*n^2), space O(d*n)
	public int minDifficulty(int[] jobDifficulty, int d) {
		if(jobDifficulty.length < d){
			return -1;
		}
		int n = jobDifficulty.length, localMax = Integer.MIN_VALUE;
		int[][] dp = new int[d][n];
		dp[0][0] = jobDifficulty[0];
		for(int i = 1; i < n; i++){
			dp[0][i] = Math.max(dp[0][i - 1], jobDifficulty[i]);
		}
		for(int k = 1; k < d; k++){
			// note that i starts from k since at least
			// k jobs needs to be done in the first k days
			for(int i = k; i < n; i++){
				dp[k][i] = Integer.MAX_VALUE;
				localMax = Integer.MIN_VALUE;
				for(int j = i; j >= k; j--){
					localMax = Math.max(localMax, jobDifficulty[j]);
					dp[k][i] = Math.min(dp[k][i], dp[k - 1][j - 1] + localMax);
				}
			}
		}
		return dp[d - 1][n - 1];
	}
}
