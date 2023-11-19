package org.wshuai.leetcode;

/**
 * Created by Wei on 09/05/2020.
 * #1572 https://leetcode.com/problems/matrix-diagonal-sum/
 */
public class MatrixDiagonalSum {

	// time O(n)
	public int diagonalSum(int[][] mat) {
		int res = 0, n = mat.length;
		for(int i = 0; i < n; i++){
			res += mat[i][i];
			if(i != n - 1 - i){
				res += mat[i][n - 1 - i];
			}
		}
		return res;
	}
}
