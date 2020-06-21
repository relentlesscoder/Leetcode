package org.wshuai.leetcode;

/**
 * Created by Wei on 11/01/2019.
 * #0813 https://leetcode.com/problems/largest-sum-of-averages/
 */
public class LargestSumOfAverages {

	// time O(n^2*k), space O(n)
	public double largestSumOfAverages(int[] A, int K) {
		int n = A.length;
		double sum = 0;
		double[] dp = new double[n];
		for(int i = 0; i < n; i++){
			sum += A[i];
			dp[i] = sum / (i + 1);
		}
		for(int k = 1; k < K; k++){
			for(int j = n - 1; j >= k; j--){
				double cur = 0;
				int count = 0;
				for(int i = j; i >= k; i--){
					cur += A[i];
					count++;
					dp[j] = Math.max(dp[j], cur / count + dp[i - 1]);
				}
			}
		}
		return dp[n - 1];
	}

	// time O(n^2*k), space O(n*k)
	public double largestSumOfAverages2D(int[] A, int K) {
		int n = A.length;
		double sum = 0;
		double[][] dp = new double[K][n];
		for(int i = 0; i < n; i++){
			sum += A[i];
			dp[0][i] = sum / (i + 1);
		}
		for(int k = 1; k < K; k++){
			for(int j = n - 1; j >= k; j--){
				double cur = 0;
				int count = 0;
				for(int i = j; i >= k; i--){
					cur += A[i];
					count++;
					dp[k][j] = Math.max(dp[k][j], cur / count + dp[k - 1][i - 1]);
				}
			}
		}
		return dp[K - 1][n - 1];
	}
}
