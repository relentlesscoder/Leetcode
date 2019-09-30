package org.wshuai.leetcode;

/**
 * Created by Wei on 9/30/2019.
 * #931 https://leetcode.com/problems/minimum-falling-path-sum/
 */
public class MinimumFallingPathSum {
	public int minFallingPathSum(int[][] A) {
		int N = A.length;
		// dp[i][j] is the minimum sum of the falling path that ends at cell A[i - 1][j - 1]
 		int[][] dp = new int[N + 1][N + 2];
		for(int i = 1; i <= N; i++){
			dp[i][0] = Integer.MAX_VALUE;
			dp[i][N + 1] = Integer.MAX_VALUE;
		}
		for(int i = 1; i <= N; i++){
			for(int j = 1; j <= N; j++){
				int min = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i - 1][j + 1]);
				dp[i][j] = min + A[i - 1][j - 1];
			}
		}
		int minSum = Integer.MAX_VALUE;
		for(int i = 1; i <= N; i++){
			minSum = Math.min(dp[N][i], minSum);
		}
		return minSum;
	}
}
