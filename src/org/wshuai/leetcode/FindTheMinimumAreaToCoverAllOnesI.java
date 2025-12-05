package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2025.
 * #3195 https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-i/
 */
public class FindTheMinimumAreaToCoverAllOnesI {

    // time O(m * n), space O(1)
    public int minimumArea(int[][] grid) {
        int m = grid.length, n = grid[0].length,
                maxRow = -1, minRow = m, maxCol = -1, minCol = n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    minRow = Math.min(minRow, i);
                    minCol = Math.min(minCol, j);
                    maxRow = Math.max(maxRow, i);
                    maxCol = Math.max(maxCol, j);
                }
            }
        }
        return (maxRow - minRow + 1) * (maxCol - minCol + 1);
    }
}
