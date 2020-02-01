package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/16.
 * #0308 https://leetcode.com/problems/range-sum-query-2d-mutable/
 */
public class RangeSumQuery2DMutable {
	private int[][] bit;
	private int[][] nums;
	private int m, n;

	public RangeSumQuery2DMutable(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return;
		}
		m = matrix.length;
		n = matrix[0].length;
		bit = new int[m + 1][n + 1];
		nums = new int[m][n];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				update(i, j, matrix[i][j]);
			}
		}
	}

	public void update(int row, int col, int val) {
		if(m == 0 || n == 0){
			return;
		}
		int diff = val - nums[row][col];
		nums[row][col] = val;
		row++;
		col++;
		for(int i = row; i <= m; i += (i & -i)){
			for(int j = col; j <= n; j += (j & -j)){
				bit[i][j] += diff;
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		if(m == 0 || n == 0){
			return 0;
		}
		return sum(row2, col2)
			+ sum(row1 - 1, col1 - 1)
			- sum(row1 - 1, col2)
			- sum(row2, col1 - 1);
	}

	private int sum(int row, int col){
		int sum = 0;
		row++;
		col++;
		for(int i = row; i > 0; i -= (i & -i)){
			for(int j = col; j > 0; j -= (j & -j)){
				sum += bit[i][j];
			}
		}
		return sum;
	}
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
