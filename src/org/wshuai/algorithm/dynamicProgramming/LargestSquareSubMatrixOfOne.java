package org.wshuai.algorithm.dynamicProgramming;

public class LargestSquareSubMatrixOfOne {

	public int largestSubMatrix(int[][] M){
		int res = 0;

		int r = M.length;
		int c = M[0].length;

		int[][] dp = new int[r][c];
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				dp[i][j] = M[i][j];

				if(i > 0 && j > 0 && M[i][j] == 1){
					dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
					res = Math.max(res, dp[i][j]);
				}
			}
		}

		return res;
	}
}
