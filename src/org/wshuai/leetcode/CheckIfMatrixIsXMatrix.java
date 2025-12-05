package org.wshuai.leetcode;

/**
 * Created by Wei on 01/07/2024.
 * #2319 https://leetcode.com/problems/check-if-matrix-is-x-matrix/
 */
public class CheckIfMatrixIsXMatrix {

    // time O(n^2), space O(1)
    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (((i == j || i + j == n - 1) && grid[i][j] == 0)
                        || ((i != j && i + j != n - 1) && grid[i][j] != 0)) {
                    return false;
                }
            }
        }
        return true;
    }
}
