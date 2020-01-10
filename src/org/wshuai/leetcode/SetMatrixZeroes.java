package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2016.
 * #0073 https://leetcode.com/problems/set-matrix-zeroes/
 */
public class SetMatrixZeroes {
	// time O(m*n), space O(1)
	public void setZeroes(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return;
		}
		int r = matrix.length, c = matrix[0].length, col0 = 1;
		// use left(top) of each row(column) to store the reset status
		for(int i = 0; i < r; i++){
			// only one exception: use col0 flag to record status for column 1
			if(matrix[i][0] == 0){
				col0 = 0;
			}
			for(int j = 1; j < c; j++){
				if(matrix[i][j] == 0){
					matrix[i][0] = matrix[0][j] = 0;
				}
			}
		}

		// reset cells from bottom right since we need the column reset flags in the first row
		for(int i = r - 1; i >= 0; i--){
			for(int j = c - 1; j >= 1; j--){
				if(matrix[i][0] == 0 || matrix[0][j] == 0){
					matrix[i][j] = 0;
				}
			}
			// finally reset the first in the row only if the first column reset flag is on
			if(col0 == 0){
				matrix[i][0] = 0;
			}
		}
	}
}
