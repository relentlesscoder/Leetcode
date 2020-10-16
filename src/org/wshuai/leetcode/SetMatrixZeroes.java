package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/08/2016.
 * #0073 https://leetcode.com/problems/set-matrix-zeroes/
 */
public class SetMatrixZeroes {

	// time O(m*n), space O(1)
	public void setZeroes(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		// easier to understand, use first row/column to record
		// column/row reset status respectively
 		boolean setFirstColumn = false, setFirstRow = false;
		for(int i = 0; i < m; i++){
			if(matrix[i][0] == 0){
				setFirstColumn = true;
			}
		}
		for(int i = 0; i < n; i++){
			if(matrix[0][i] == 0){
				setFirstRow = true;
			}
		}
		for(int i = 1; i < m; i++){
			for(int j = 1; j < n; j++){
				if(matrix[i][j] == 0){
					matrix[i][0] = matrix[0][j] = 0;
				}
			}
		}
		for(int i = 1; i < m; i++){
			for(int j = 1; j < n; j++){
				if(matrix[i][0] == 0 || matrix[0][j] == 0){
					matrix[i][j] = 0;
				}
			}
		}
		if(setFirstRow){
			Arrays.fill(matrix[0], 0);
		}
		if(setFirstColumn){
			for(int i = 0; i < m; i++){
				matrix[i][0] = 0;
			}
		}
	}

	// time O(m*n), space O(1)
	public void setZeroesFirstColumn(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		boolean setColumnZero = false;
		// use first cell of each row to store the row reset status
		for(int i = 0; i < m; i++){
			// one exception: use boolean flag to record status for column 1
			if(matrix[i][0] == 0){
				setColumnZero = true;
			}
			for(int j = 1; j < n; j++){
				if(matrix[i][j] == 0){
					matrix[i][0] = matrix[0][j] = 0;
				}
			}
		}
		// reset cells from bottom right since we need the column reset flags in the first row
		for(int i = m - 1; i >= 0; i--){
			for(int j = n - 1; j >= 1; j--){
				if(matrix[i][0] == 0 || matrix[0][j] == 0){
					matrix[i][j] = 0;
				}
			}
			// reset the first cell in the row only if the first column reset flag is on
			if(setColumnZero){
				matrix[i][0] = 0;
			}
		}
	}
}
