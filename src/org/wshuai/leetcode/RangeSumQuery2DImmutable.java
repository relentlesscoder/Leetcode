package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2016.
 * #0304 https://leetcode.com/problems/range-sum-query-2d-immutable/
 */
public class RangeSumQuery2DImmutable {

	private int m, n;
	private int[][] prefix;

	public RangeSumQuery2DImmutable(int[][] matrix) {
		if(matrix != null && matrix.length > 0 && matrix[0].length > 0){
			m = matrix.length;
			n = matrix[0].length;
			prefix = new int[m][n + 1];
			for(int i = 0; i < m; i++){
				for(int j = 1; j <= n; j++){
					prefix[i][j] = prefix[i][j - 1] + matrix[i][j - 1];
				}
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		if(m == 0 || n == 0){
			return 0;
		}
		int res = 0;
		for(int i = row1; i <= row2; i++){
			res += prefix[i][col2 + 1] - prefix[i][col1];
		}
		return res;
	}
}
