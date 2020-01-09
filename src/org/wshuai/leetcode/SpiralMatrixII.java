package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2016.
 * #0059 https://leetcode.com/problems/spiral-matrix-ii/
 */
public class SpiralMatrixII {
	// O(n^2)
	public int[][] generateMatrix(int n) {
		int[][] res = new int[n][n];
		int cur = 1, up = 0, down = n - 1, left = 0, right = n - 1, max = n*n;
		while(cur <= max){
			for(int i = left; i <= right && cur <= max; i++){
				res[up][i] = cur++;
			}
			for(int j = up + 1; j <= down - 1 && cur <= max; j++){
				res[j][right] = cur++;
			}
			for(int i = right; i >= left && cur <= max; i--){
				res[down][i] = cur++;
			}
			for(int j = down - 1; j >= up + 1 && cur <= max; j--){
				res[j][left] = cur++;
			}
			up++;
			down--;
			left++;
			right--;
		}
		return res;
	}
}
