package org.wshuai.algorithm.dynamicProgramming;

public class LongestCommonSubstring {

	public int getLCSLength(String A, String B){

		int r = A.length();
		int c = B.length();
		int[][] dp = new int[r + 1][c + 1];
		int max = 0;

		for(int i = 1; i <= r; i++){
			for(int j = 1; j <= c; j++){
				if(A.charAt(i - 1) == B.charAt(j - 1)){
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
