package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/08/2016.
 * #0054 https://leetcode.com/problems/spiral-matrix/
 */
public class SpiralMatrix {

	// time O(m*n)
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<>();
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return res;
		}
		int m = matrix.length, n = matrix[0].length, size = m*n;
		for(int left = 0, right = n - 1, top = 0, bottom = m - 1; res.size() < size; left++, right--, top++, bottom--){
			// left to right
			for(int i = left; i <= right && res.size() < size; i++){
				res.add(matrix[top][i]);
			}
			// top to bottom
			for(int i = top + 1; i <= bottom - 1 && res.size() < size; i++){
				res.add(matrix[i][right]);
			}
			// right to left
			for(int i = right; i >= left && res.size() < size; i--){
				res.add(matrix[bottom][i]);
			}
			// bottom to top
			for(int i = bottom - 1; i >= top + 1 && res.size() < size; i--){
				res.add(matrix[i][left]);
			}
		}
		return res;
	}
}
