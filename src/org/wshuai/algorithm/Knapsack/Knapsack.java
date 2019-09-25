package org.wshuai.algorithm.Knapsack;

import org.junit.Test;

public class Knapsack {

	@Test
	public void testcase(){
		int max = getMaxValue(10, new int[]{10, 40, 30, 50}, new int[]{5, 4, 6, 3});
	}

	public int getMaxValue(int M, int[] values, int[] weights){
		int N = values.length;
		int[][] dp = new int[N+1][M+1];

		for(int i = 1; i <= N; i++ ){
			for(int j = 1; j <= M; j++){
				if(j >= weights[i-1]){
					dp[i][j] = Math.max(dp[i-1][j], values[i-1] + dp[i-1][j-weights[i-1]]);
				}else{
					dp[i][j] = dp[i-1][j];
				}
			}
		}

		return dp[N][M];
	}
}
