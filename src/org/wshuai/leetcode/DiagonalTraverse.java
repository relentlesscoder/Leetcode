package org.wshuai.leetcode;

/**
 * Created by Wei on 02/19/2017.
 * #0498 https://leetcode.com/problems/diagonal-traverse/?tab=Description
 */
public class DiagonalTraverse {
	// time O(m*n)
	public int[] findDiagonalOrder(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return new int[0];
		}
		int m = matrix.length, n = matrix[0].length, i = 0, j = 0, d = 1;
		int[] res = new int[m * n];
		for(int k = 0; k < res.length; k++){
			res[k] = matrix[i][j];
			i -= d;
			j += d;
			if(i == m){
				i = m - 1;
				j += 2;
				d = -d;
			}
			if(j == n){
				j = n - 1;
				i += 2;
				d = -d;
			}
			if(i < 0){
				i = 0;
				d = -d;
			}
			if(j < 0){
				j = 0;
				d = -d;
			}
		}
		return res;
	}
}
