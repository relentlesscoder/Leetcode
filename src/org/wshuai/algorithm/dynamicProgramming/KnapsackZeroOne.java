package org.wshuai.algorithm.dynamicProgramming;

public class KnapsackZeroOne {

	public int getMaxValue(int M, int[] values, int[] weights){
		int r = weights.length;
		int c = M;
		int[][] dp = new int[r + 1][c + 1];

		for(int i = 1; i <= r; i++){
			for(int j = 1; j <= M; j++){
				if(weights[i - 1] <= j){
					dp[i][j] = Math.max(dp[i - 1][j],
						dp[i - 1][j - weights[i - 1]] + values[i - 1]);
				}else{
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		return dp[r][c];
	}

}
