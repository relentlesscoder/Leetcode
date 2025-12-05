package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2016.
 * #0308 https://leetcode.com/problems/range-sum-query-2d-mutable/
 */
public class RangeSumQuery2DMutable {
	private class NumMatrixBinaryIndexedTree {

		private final int[][] matrix;
		private final BIT bit;

		// time O(m * n * log(m * n)), space O(m * n)
		public NumMatrixBinaryIndexedTree(int[][] matrix) {
			this.matrix = matrix;
			bit = new BIT(matrix);
		}

		// time O(log(m * n)), space O(1)
		public void update(int row, int col, int val) {
			int delta = val - matrix[row][col];
			matrix[row][col] = val;
			bit.update(row + 1, col + 1, delta);
		}

		// time O(log(m * n)), space O(1)
		public int sumRegion(int row1, int col1, int row2, int col2) {
			return bit.query(row1 + 1, col1 + 1, row2 + 1, col2 + 1);
		}

		private static class BIT {

			private final int[][] tree;
			private final int m;
			private final int n;

			public BIT(int[][] matrix) {
				m = matrix.length + 1;
				n = matrix[0].length + 1;
				tree = new int[m][n];
				for (int i = 1; i < m; i++) {
					for (int j = 1; j < n; j++) {
						update(i, j, matrix[i - 1][j - 1]);
					}
				}
			}

			public void update(int row, int col, int val) {
				for (int i = row; i < m; i += i & -i) {
					for (int j = col; j < n; j += j & -j) {
						tree[i][j] += val;
					}
				}
			}

			public int sum(int row, int col) {
				int res = 0;
				for (int i = row; i > 0; i -= i & -i) {
					for (int j = col; j > 0; j -= j & -j) {
						res += tree[i][j];
					}
				}
				return res;
			}

			public int query(int row1, int col1, int row2, int col2) {
				return sum(row2, col2) - sum(row1 - 1, col2) - sum(row2, col1 - 1) + sum(row1 - 1, col1 - 1);
			}
		}
	}
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
