package org.wshuai.leetcode;

/**
 * Created by Wei on 12/3/19.
 * #1277 https://leetcode.com/problems/count-square-submatrices-with-all-ones/
 */
public class CountSquareSubmatricesWithAllOnes {
	// https://www.techiedelight.com/find-size-largest-square-sub-matrix-1s-present-given-binary-matrix/
	public int countSquares(int[][] matrix) {
		int R = matrix.length;
		int C = matrix[0].length;
		int[][] dp = new int[R][C];
		int res = 0;
		for(int i = 0; i < R; i++){
			for(int j = 0; j < C; j++){
				dp[i][j] = matrix[i][j];
				if(i > 0 && j > 0 && matrix[i][j] == 1){
					dp[i][j] = Math.min(dp[i - 1][j - 1],
							Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
				}
				res += dp[i][j];
			}
		}
		return res;
	}
}
