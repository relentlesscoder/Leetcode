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
		int res = 0, m = matrix.length, n = matrix[0].length, prev = 0, cur = 1;
		int[][] dp = new int[2][n];
		for(int i = 0; i < n; i++){
			dp[prev][i] = matrix[0][i] - '0';
			res = Math.max(res, dp[prev][i]);
		}
		for(int i = 1; i < m; i++){
			for(int j = 0; j < n; j++){
				if(j == 0 || matrix[i][j] == '0'){
					dp[cur][j] = matrix[i][j] - '0';
				}else{
					dp[cur][j] = Math.min(dp[prev][j - 1],
						Math.min(dp[cur][j - 1], dp[prev][j])) + 1;
				}
				res = Math.max(res, dp[cur][j]);
			}
			prev = cur;
			cur = 1 - cur;
		}
		return res * res;
	}

	// time O(r*c), space O(r*c)
	public int maximalSquare2D(char[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return 0;
		}
		int res = 0, m = matrix.length, n = matrix[0].length;
		int[][] dp = new int[m][n];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(i == 0 || j == 0 || matrix[i][j] == '0'){
					dp[i][j] = matrix[i][j] - '0';
				}else{
					dp[i][j] = Math.min(dp[i - 1][j - 1],
						Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
				}
				res = Math.max(res, dp[i][j]);
			}
		}
		return res * res;
	}
}
