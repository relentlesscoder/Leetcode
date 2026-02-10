package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 02/09/2026.
 * #2664 https://leetcode.com/problems/the-knights-tour/
 */
public class TheKnightsTour {

    private static final int[][] DIRS = new int[][]{
            {1, 2}, {1, -2},
            {2, 1}, {2, -1},
            {-1, 2}, {-1, -2},
            {-2, 1}, {-2, -1}
    };

    // time O(8^(m * n)), space O(m * n)
    public int[][] tourOfKnight(int m, int n, int r, int c) {
        // 暴力搜索
        int[][] grid = new int[m][n];
        for (int[] row : grid) {
            Arrays.fill(row, -1);
        }
        dfs(0, r, c, grid);
        return grid;
    }

    private boolean dfs(int k, int r, int c, int[][] grid) {
        int m = grid.length, n = grid[0].length;
        grid[r][c] = k;
        if (k == m * n - 1) {
            return true;
        }
        for (int i = 0; i < DIRS.length; i++) {
            int x = r + DIRS[i][0], y = c + DIRS[i][1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == -1
                    && dfs(k + 1, x, y, grid)) {
                return true;
            }
        }
        grid[r][c] = -1;
        return false;
    }
}
