package org.wshuai.leetcode;

/**
 * Created by Wei on 8/21/19.
 * #766 https://leetcode.com/problems/toeplitz-matrix/
 */
public class ToeplitzMatrix {
	public boolean isToeplitzMatrix(int[][] matrix) {
		int r = matrix.length;
		int c = matrix[0].length;
		for (int i = 0; i < r; i++) {
			//compare to the upper left Neighbor
			for (int j = 0; j < c; j++) {
				if (i > 0 && j > 0 && matrix[i][j] != matrix[i - 1][j - 1]) {
					return false;
				}
			}
		}
		return true;
	}
}
