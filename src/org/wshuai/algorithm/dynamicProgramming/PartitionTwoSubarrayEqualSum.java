package org.wshuai.algorithm.dynamicProgramming;

import java.util.Arrays;

public class PartitionTwoSubarrayEqualSum {
	public boolean partitionEqualSum(int[] M){
		int sum = 0;
		for(int m: M){
			sum += m;
		}
		if(sum % 2 != 0){
			return false;
		}
		int r = M.length;
		int c = sum / 2;
		boolean[][] dp = new boolean[r + 1][c + 1];
		Arrays.fill(dp[0], false);
		for(int i = 0; i <= r; i++){
			dp[i][0] = true;
		}
		for(int i = 1; i <= r; i++){
			for(int j = 1; j <= c; j++){
				if(M[i - 1] <= j){
					dp[i][j] = dp[i - 1][j] || dp[i - 1][j - M[i - 1]];
				}else{
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[r][c];
	}
}
