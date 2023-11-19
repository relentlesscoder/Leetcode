package org.wshuai.leetcode;

/**
 * Created by Wei on 01/25/2020.
 * #0240 https://leetcode.com/problems/search-a-2d-matrix-ii/
 */
public class SearchA2DMatrixII {

	// time O(r + c)
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return false;
		}
		int r = matrix.length, c = matrix[0].length, i = 0, j = c - 1;
		while(i < r && j >= 0){
			if(matrix[i][j] > target){
				j--;
			}else if(matrix[i][j] < target){
				i++;
			}else{
				return true;
			}
		}
		return false;
	}
}
