package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #48 https://leetcode.com/problems/rotate-image/
 */
public class RotateImage {

	//Use property temp[j][i] = matrix[rows-i-1][j];
	public void rotate(int[][] matrix) {
		if (matrix == null) {
			return;
		}
		int rows = matrix.length;
		if (rows == 0) {
			return;
		}
		int cols = matrix[0].length;
		if (rows != cols) {
			return;
		}
		int len = rows;
		int start = 0;
		int end = len - 1;
		while (start < end) {
			for (int i = start; i < end; i++) {
				int temp = matrix[start][i];
				matrix[start][i] = matrix[len - i - 1][start];
				matrix[len - i - 1][start] = matrix[len - start - 1][len - i - 1];
				matrix[len - start - 1][len - i - 1] = matrix[i][len - start - 1];
				matrix[i][len - start - 1] = temp;
			}
			start++;
			end--;
		}
	}

	//Use extra space
	public int[][] rotateExtraSpace(int[][] matrix) {
		if (matrix == null) {
			return matrix;
		}
		int rows = matrix.length;
		if (rows == 0) {
			return matrix;
		}
		int cols = matrix[0].length;
		if (rows != cols) {
			return matrix;
		}
		int[][] temp = new int[rows][rows];
		for (int i = rows - 1; i >= 0; i--) {
			for (int j = 0; j < rows; j++) {
				temp[j][i] = matrix[rows - i - 1][j];
			}
		}
		return temp;
	}
}
