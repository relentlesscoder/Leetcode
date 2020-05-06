package org.wshuai.leetcode;

/**
 * Created by Wei on 08/07/2019.
 * #0832 https://leetcode.com/problems/flipping-an-image/
 */
public class FlippingAnImage {

	// time O(m*n), space O(m*n)
	public int[][] flipAndInvertImage(int[][] A) {
		int m = A.length, n = A[0].length;
		int[][] res = new int[m][n];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				res[i][n - 1 - j] = 1 - A[i][j];
			}
		}
		return res;
	}
}
