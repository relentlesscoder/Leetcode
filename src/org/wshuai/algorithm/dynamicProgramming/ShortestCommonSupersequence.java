package org.wshuai.algorithm.dynamicProgramming;

public class ShortestCommonSupersequence {

	public int shortestCommonSupersequence(String A, String B){
		int r = A.length();
		int c = B.length();

		int[][] dp = new int[r + 1][c + 1];
		for(int i = 0; i <= r; i++){
			dp[i][0] = i;
		}
		for(int j = 1; j <= c; j++){
			dp[0][j] = j;
		}
		for(int i = 1; i <= r; i++){
			for(int j = 1; j <= c; j++){
				if(A.charAt(i - 1) == B.charAt(j - 1)){
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}else{
					dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
				}
			}
		}

		return dp[r][c];
	}
}
