package org.wshuai.leetcode;

/**
 * Created by Wei on 08/21/2019.
 * #0766 https://leetcode.com/problems/toeplitz-matrix/
 */
public class ToeplitzMatrix {

	// time O(m*n)
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

	// time O(m*n)
	public boolean isToeplitzMatrixDiagonalTraversal(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		//upper diagonal
		for(int i = 0; i < m || i < n; i++){
			for(int x = 0, y = i; x < m && y < n; x++, y++){
				if(x > 0 && y > 0){
					if(matrix[x][y] != matrix[x - 1][y - 1]){
						return false;
					}
				}
			}
		}
		//lower diagonal
		for(int i = 0; i < m || i < n; i++){
			int count = 0;
			for(int x = i, y = 0; x < m && y < n; x++, y++){
				if(x > 0 && y > 0){
					if(matrix[x][y] != matrix[x - 1][y - 1]){
						return false;
					}
				}
			}
		}

		return true;
	}
}
