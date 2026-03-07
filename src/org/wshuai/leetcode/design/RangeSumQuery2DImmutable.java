package org.wshuai.leetcode.design;

/**
 * Created by Wei on 09/25/2016.
 * #0304 https://leetcode.com/problems/range-sum-query-2d-immutable/
 */
public class RangeSumQuery2DImmutable {

    private static class NumMatrix {

        private final int[][] prefix;

        // time O(m * n), space O(m * n)
        public NumMatrix(int[][] matrix) {
			// https://leetcode.cn/problems/range-sum-query-2d-immutable/solutions/2667331/tu-jie-yi-zhang-tu-miao-dong-er-wei-qian-84qp/
            int m = matrix.length, n = matrix[0].length;
            prefix = new int[m + 1][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    prefix[i + 1][j + 1] = prefix[i + 1][j] + prefix[i][j + 1] - prefix[i][j] + matrix[i][j];
                }
            }
        }

        // time O(1), space O(1)
        public int sumRegion(int row1, int col1, int row2, int col2) {
            return prefix[row2 + 1][col2 + 1] - prefix[row2 + 1][col1] - prefix[row1][col2 + 1] + prefix[row1][col1];
        }
    }

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
}
