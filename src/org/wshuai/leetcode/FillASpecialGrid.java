package org.wshuai.leetcode;

/**
 * Created by Wei on 10/07/2025.
 * #3537 https://leetcode.com/problems/fill-a-special-grid/
 */
public class FillASpecialGrid {

    private int num = 0;

    // time O(2^2n), space O(2^2n)
    public int[][] specialGrid(int n) {
        if (n == 0) {
            return new int[1][1];
        }
        int m = 1 << n, k = (m >> 1);
        int[][] res = new int[m][m];
        buildGrid(res, 0, k - 1,  k, m - 1); // n - 1 recursion stacks
        buildGrid(res, k, m - 1,  k, m - 1);
        buildGrid(res, k, m - 1,  0, k - 1);
        buildGrid(res, 0, k - 1,  0, k - 1);
        return res;
    }

    private void buildGrid(int[][] grid, int startRow, int endRow, int startCol, int endCol) {
        if (startRow == endRow && startCol == endCol) {
            grid[startRow][startCol] = num++;
            return;
        }
        int m = endRow - startRow + 1, k = m >> 1;
        buildGrid(grid, startRow, startRow + k - 1, startCol + k, endCol);
        buildGrid(grid, startRow + k, endRow, startCol + k, endCol);
        buildGrid(grid, startRow + k, endRow,  startCol, startCol + k - 1);
        buildGrid(grid, startRow, startRow + k - 1,  startCol, startCol + k - 1);
    }
}
