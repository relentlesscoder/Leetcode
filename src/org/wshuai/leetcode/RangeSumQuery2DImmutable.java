package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2016.
 * #0304 https://leetcode.com/problems/range-sum-query-2d-immutable/
 */
public class RangeSumQuery2DImmutable {
	private int[][] prefixSum;
	private int R, C;

	// time O(r*c)
	public RangeSumQuery2DImmutable(int[][] matrix) {
		if(matrix != null && matrix.length > 0 && matrix[0].length > 0){
			int R = matrix.length;
			int C = matrix[0].length;
			prefixSum = new int[R][C + 1];
			for(int i = 0; i < R; i++){
				for(int j = 1; j <= C; j++){
					prefixSum[i][j] = prefixSum[i][j - 1] + matrix[i][j - 1];
				}
			}
		}
	}

	// time O(c)
	public int sumRegion(int row1, int col1, int row2, int col2) {
		if(!validate(row1, col1) || !validate(row2, col2)){
			return -1;
		}
		int sum = 0;
		for(int i = row1; i <= row2; i++){
			sum += prefixSum[i][col2 + 1] - prefixSum[i][col1];
		}
		return sum;
	}

	private boolean validate(int row, int col) {
		return row >= 0 && col >= 0 && row < R && col < C;
	}
}
