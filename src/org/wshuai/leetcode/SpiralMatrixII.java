package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2016.
 * #0059 https://leetcode.com/problems/spiral-matrix-ii/
 */
public class SpiralMatrixII {

	// time O(n^2)
	public int[][] generateMatrix(int n) {
		int[][] matrix = new int[n][n];
		int cur = 1, size = n*n;
		for(int left = 0, right = n - 1, top = 0, bottom = n - 1; cur <= size; left++, right--, top++, bottom--){
			for(int i = left; i <= right && cur <= size; i++){
				matrix[top][i] = cur++;
			}
			for(int i = top + 1; i <= bottom - 1 && cur <= size; i++){
				matrix[i][right] = cur++;
			}
			for(int i = right; i >= left && cur <= size; i--){
				matrix[bottom][i] = cur++;
			}
			for(int i = bottom - 1; i >= top + 1 && cur <= size; i--){
				matrix[i][left] = cur++;
			}
		}
		return matrix;
	}
}
