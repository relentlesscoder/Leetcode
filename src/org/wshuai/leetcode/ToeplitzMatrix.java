package org.wshuai.leetcode;

/**
 * Created by Wei on 08/21/2019.
 * #0766 https://leetcode.com/problems/toeplitz-matrix/
 */
public class ToeplitzMatrix {

	// time O(m * n), space O(1)
	public boolean isToeplitzMatrix(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		for (int i = 0; i < m; i++) {
			//compare to the upper left Neighbor
			for (int j = 0; j < n; j++) {
				if (i > 0 && j > 0 && matrix[i][j] != matrix[i - 1][j - 1]) {
					return false;
				}
			}
		}
		return true;
	}

	// time O(m * n), space O(1)
	public boolean isToeplitzMatrixDiagonalTraversal(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		for (int k = 0; k < n; k++) {
			int val = matrix[0][k];
			for (int i = 0, j = k; i < m && j < n; i++, j++) { // each loop starts from [0, 0] ... [0, 1] ... [0, 2]
				if (matrix[i][j] != val) {
					return false;
				}
			}
		}
		for (int k = 0; k < m; k++) {
			int val = matrix[k][0];
			for (int i = k, j = 0; i < m && j < n; i++, j++) { // each loop starts from [0, 0] ... [1, 0] ... [2, 0]
				if (matrix[i][j] != val) {
					return false;
				}
			}
		}
		return true;
	}
}
