package org.wshuai.leetcode;

/**
 * Created by Wei on 8/22/19.
 * #867 https://leetcode.com/problems/transpose-matrix/
 */
public class TransposeMatrix {
	public int[][] transpose(int[][] A) {
		int r = A.length;
		int c = A[0].length;
		int[][] res = new int[c][r];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				res[j][i] = A[i][j];
			}
		}
		return res;
	}
}
