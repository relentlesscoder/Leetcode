package org.wshuai.leetcode;

/**
 * Created by Wei on 10/05/2016.
 * #0221 https://leetcode.com/problems/maximal-square/
 */
public class MaximalSquare {
	// time O(r*c), space O(c)
	// https://leetcode.com/problems/count-square-submatrices-with-all-ones/discuss/443314/java-dp-with-explanation/423971
	public int maximalSquare(char[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return 0;
		}
		int res = 0, r = matrix.length, c = matrix[0].length, prev = 0, cur = 1;
		int[][] dp = new int[2][c];
		for(int i = 0; i < c; i++){
			dp[prev][i] = matrix[0][i] == '1' ? 1 : 0;
			res = Math.max(res, dp[prev][i]);
		}
		for(int i = 1; i < r; i++){
			for(int j = 0; j < c; j++){
				if(j == 0){
					dp[cur][j] = matrix[i][j] == '1' ? 1 : 0;
				}else if(matrix[i][j] == '0'){
					dp[cur][j] = 0;
				}else{
					dp[cur][j] = Math.min(dp[prev][j - 1],
						Math.min(dp[prev][j], dp[cur][j - 1])) + 1;
				}
				res = Math.max(res, dp[cur][j]);
			}
			prev = cur;
			cur = 1 - prev;
		}
		return res * res;
	}

	// time O(r*c), space O(r*c)
	public int maximalSquare2D(char[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return 0;
		}
		int res = 0, r = matrix.length, c = matrix[0].length;
		int[][] dp = new int[r][c];
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(i == 0 || j == 0){
					dp[i][j] = matrix[i][j] == '1' ? 1 : 0;
				}else if(matrix[i][j] == '0'){
					dp[i][j] = 0;
				}else{
					dp[i][j] = Math.min(dp[i - 1][j - 1],
						Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
				}
				res = Math.max(res, dp[i][j]);
			}
		}
		return res * res;
	}
}
