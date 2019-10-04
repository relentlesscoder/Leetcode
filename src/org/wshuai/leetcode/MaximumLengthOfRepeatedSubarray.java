package org.wshuai.leetcode;

/**
 * Created by Wei on 10/4/19.
 * #718 https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 */
public class MaximumLengthOfRepeatedSubarray {

	// 17 ms
	public int findLengthSpaceOptimization(int[] A, int[] B) {
		int max = 0;
		int r = A.length;
		int c = B.length;
		int[] dp = new int[c + 1];
		for(int i = 1; i <= r; i++){
			int prev = dp[0];
			for(int j = 1; j <= c; j++){
				int temp = dp[j];
				if(A[i - 1] == B[j - 1]){
					dp[j] = prev + 1;
					max = Math.max(dp[j], max);
				}else{
					dp[j] = 0;
				}
				prev = temp;
			}
		}
		return max;
	}

	// 53 ms
	public int findLength(int[] A, int[] B) {
		int max = 0;
		int r = A.length;
		int c = B.length;
		int[][] dp = new int[r + 1][c + 1];
		for(int i = 1; i <= r; i++){
			for(int j = 1; j <= c; j++){
				if(A[i - 1] == B[j - 1]){
					dp[i][j] = dp[i - 1][j - 1] + 1;
					max = Math.max(dp[i][j], max);
				}else{
					dp[i][j] = 0;
				}
			}
		}
		return max;
	}
}
