package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2016.
 * #0311 https://leetcode.com/problems/sparse-matrix-multiplication/
 */
public class SparseMatrixMultiplication {

	// time O(r1*c1*c2)
	public int[][] multiply(int[][] A, int[][] B) {
		int ra = A.length, ca = A[0].length, cb = B[0].length;
		int[][] res = new int[ra][cb];
		for(int i = 0; i < ra; i++){
			for(int j = 0; j < ca; j++){
				if(A[i][j] == 0){
					continue;
				}
				for(int k = 0; k < cb; k++){
					res[i][k] += A[i][j] * B[j][k];
				}
			}
		}
		return res;
	}
}
