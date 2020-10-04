package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2020.
 * #1605 https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums/
 */
public class FindValidMatrixGivenRowAndColumnSums {

	// time O(m+n), space O(m*n)
	public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
		int[][] res = new int[rowSum.length][colSum.length];
		int i = 0, j = 0;
		while(i < rowSum.length && j < colSum.length){
			int min = Math.min(rowSum[i], colSum[j]);
			res[i][j] = min;
			if((rowSum[i] -= min) == 0){
				i++;
			}
			if((colSum[j] -= min) == 0){
				j++;
			}
		}
		return res;
	}
}
