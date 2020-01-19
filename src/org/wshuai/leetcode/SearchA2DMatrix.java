package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2016.
 * 0074 https://leetcode.com/problems/search-a-2d-matrix/
 */
public class SearchA2DMatrix {
	// time log(r*c)
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return false;
		}
		int r = matrix.length, c = matrix[0].length, left = 0, right = r * c - 1;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(matrix[mid / c][mid % c] < target){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return matrix[left / c][left % c] == target;
	}
}
