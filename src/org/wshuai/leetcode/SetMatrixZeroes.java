package org.wshuai.leetcode;

/**
 * Created by Wei on 10/8/16.
 * #73 https://leetcode.com/problems/set-matrix-zeroes/
 */
public class SetMatrixZeroes {
	public void setZeroes(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}

		int rows = matrix.length;
		int cols = matrix[0].length;

		//Using first row and column to record if
		// the rest rows and columns need to be set to zeros
		boolean setFirstRow = false;
		boolean setFirstCol = false;

		for (int i = 0; i < cols; i++) {
			if (matrix[0][i] == 0) {
				setFirstRow = true;
				break;
			}
		}

		for (int i = 0; i < rows; i++) {
			if (matrix[i][0] == 0) {
				setFirstCol = true;
				break;
			}
		}

		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < cols; j++) {
				if (matrix[i][j] == 0) {
					matrix[0][j] = 0;
					matrix[i][0] = 0;
				}
			}
		}

		for (int i = 1; i < cols; i++) {
			if (matrix[0][i] == 0) {
				setColZeros(i, matrix, rows);
			}
		}

		for (int i = 1; i < rows; i++) {
			if (matrix[i][0] == 0) {
				setRowZeros(i, matrix, cols);
			}
		}

		if (setFirstRow) {
			setRowZeros(0, matrix, cols);
		}

		if (setFirstCol) {
			setColZeros(0, matrix, rows);
		}
	}

	private void setRowZeros(int row, int[][] matrix, int cols) {
		for (int i = 0; i < cols; i++) {
			matrix[row][i] = 0;
		}
	}

	private void setColZeros(int col, int[][] matrix, int rows) {
		for (int i = 0; i < rows; i++) {
			matrix[i][col] = 0;
		}
	}
}
