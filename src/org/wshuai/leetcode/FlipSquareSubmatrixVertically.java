package org.wshuai.leetcode;

/**
 * Created by Wei on 12/03/2025.
 * #3643 https://leetcode.com/problems/flip-square-submatrix-vertically/
 */
public class FlipSquareSubmatrixVertically {

    // time O(k^2), space O(1)
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        for (int col = y; col < y + k; col++) {
            for (int i = x, j = x + k - 1; i < j; i++, j--) {
                int temp = grid[i][col];
                grid[i][col] = grid[j][col];
                grid[j][col] = temp;
            }
        }
        return grid;
    }
}
