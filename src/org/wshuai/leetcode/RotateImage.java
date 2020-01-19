package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #0048 https://leetcode.com/problems/rotate-image/
 */
public class RotateImage {
	/*
	 * clockwise rotate
	 * first reverse up to down, then swap the symmetry
	 * 1 2 3     7 8 9     7 4 1
	 * 4 5 6  => 4 5 6  => 8 5 2
	 * 7 8 9     1 2 3     9 6 3
	 */
	public void rotate(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return;
		}
		int r = matrix.length;
		int c = matrix[0].length;
		for(int i = 0, j = r - 1; i < j; i++, j--){
			int[] temp = matrix[i];
			matrix[i] = matrix[j];
			matrix[j] = temp;
		}
		for(int i = 0; i < r; i++){
			for(int j = i + 1; j < c; j++){
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
	}

	/*
	 * anticlockwise rotate
	 * first reverse left to right, then swap the symmetry
	 * 1 2 3     3 2 1     3 6 9
	 * 4 5 6  => 6 5 4  => 2 5 8
	 * 7 8 9     9 8 7     1 4 7
	 */
	/*public void antiRotate(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return;
		}
		int r = matrix.length;
		int c = matrix[0].length;
		for(int i = 0, j = c - 1; i < j; i++, j--){
			for(int k = 0; k < r; k++){
				int temp = matrix[k][i];
				matrix[k][i] = matrix[k][j];
				matrix[k][j] = temp;
			}
		}
		for(int i = 0; i < r; i++){
			for(int j = i + 1; j < c; j++){
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
	}*/
}
