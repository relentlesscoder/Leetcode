package org.wshuai.leetcode;

/**
 * Created by Wei on 01/12/2020.
 * #1314 https://leetcode.com/problems/matrix-block-sum/
 */
public class MatrixBlockSum {
	// time O(m*n), space O(m*n)
	// https://leetcode.com/problems/matrix-block-sum/discuss/477036/JavaPython-3-PrefixRange-sum-w-analysis-similar-to-LC-30478
	public int[][] matrixBlockSum(int[][] mat, int K) {
		int m = mat.length, n = mat[0].length;
		int[][] rangeSum = new int[m + 1][n + 1];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				rangeSum[i + 1][j + 1] = rangeSum[i + 1][j] + rangeSum[i][j + 1] - rangeSum[i][j] + mat[i][j];
			}
		}
		int[][] res = new int[m][n];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				int r1 = Math.max(0, i - K), c1 = Math.max(0, j - K), r2 = Math.min(m, i + K + 1), c2 = Math.min(n, j + K + 1);
				res[i][j] = rangeSum[r2][c2] - rangeSum[r2][c1] - rangeSum[r1][c2] + rangeSum[r1][c1];
			}
		}
		return res;
	}
}
