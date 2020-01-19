package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/08/2016.
 * #0054 https://leetcode.com/problems/spiral-matrix/
 */
public class SpiralMatrix {
	// O(m*n)
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<>();
		if(matrix == null || matrix.length == 0){
			return res;
		}
		int n = matrix.length, m = matrix[0].length;
		int up = 0, down = n - 1, left = 0, right = m - 1, total = n*m;
		while(res.size() < total){
			// left to right
			for(int j = left; j <= right && res.size() < total; j++){
				res.add(matrix[up][j]);
			}
			// up to down
			for(int i = up + 1; i <= down - 1 && res.size() < total; i++){
				res.add(matrix[i][right]);
			}
			//right to left
			for(int j = right; j >= left && res.size() < total; j--){
				res.add(matrix[down][j]);
			}
			//down to up
			for(int i = down - 1; i >= up + 1 && res.size() < total; i--){
				res.add(matrix[i][left]);
			}
			left++;
			right--;
			up++;
			down--;
		}
		return res;
	}
}
