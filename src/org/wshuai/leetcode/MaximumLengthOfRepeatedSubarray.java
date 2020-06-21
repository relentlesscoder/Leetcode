package org.wshuai.leetcode;

/**
 * Created by Wei on 10/04/2019.
 * #0718 https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 */
public class MaximumLengthOfRepeatedSubarray {

	// time O(r*c), space O(c)
	public int findLengthSpaceOptimization(int[] A, int[] B) {
		int max = 0, r = A.length, c = B.length;
		int[] dp = new int[c + 1];
		for(int i = 1; i <= r; i++){
			// use variable prev to record dp[i - 1][j - 1]
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

	// time O(r*c), space O(2*c)
	public int findLengthRollingArray(int[] A, int[] B) {
		int res = 0, r = A.length, c = B.length;
		int[][] dp = new int[2][c + 1];
		int prev = 0, cur = 1;
		for(int i = 1; i <= r; i++){
			for(int j = 1; j <= c; j++){
				if(A[i - 1] == B[j - 1]){
					dp[cur][j] = dp[prev][j - 1] + 1;
					res = Math.max(res, dp[cur][j]);
				}else{
					dp[cur][j] = 0;
				}
			}
			cur = prev;
			prev = 1 - cur;
		}
		return res;
	}

	// time O(r*c), space O(r*c)
	public int findLength2D(int[] A, int[] B) {
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
