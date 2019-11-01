package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/2019.
 * #813 https://leetcode.com/problems/largest-sum-of-averages/
 */
public class LargestSumOfAverages {
	public double largestSumOfAverages(int[] A, int K) {
		int N = A.length;
		double[] dp = new double[N];
		double sum = 0;
		for(int i = 0; i < N; i++){
			sum += A[i];
			dp[i] = sum / (i + 1);
		}
		for(int k = 1; k < K; k++){
			for(int i = N - 1; i >= k; i--){
				double max = 0;
				double curr = 0;
				int cnt = 0;
				for(int j = i; j >= k; j--){
					curr += A[j];
					cnt++;
					max = Math.max(max, curr / cnt + dp[j - 1]);
				}
				dp[i] = max;
			}
		}
		return dp[N - 1];
	}
}
