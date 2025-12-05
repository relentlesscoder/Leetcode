package org.wshuai.leetcode;

/**
 * Created by Wei on 11/03/2025.
 * #2257 https://leetcode.com/problems/count-unguarded-cells-in-the-grid/
 */
public class CountUnguardedCellsInTheGrid {

    private static final int[] DIRS = new int[] {0, -1, 0, 1, 0};

    // time O(g * (m + n)), space O(m * n)
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int res = 0;
        int[][] grid = new int[m][n];
        for (int[] g : guards) {
            grid[g[0]][g[1]] = 1;
        }
        for (int[] w : walls) {
            grid[w[0]][w[1]] = 1;
        }
        for (int[] g : guards) {
            for (int d = 0; d < 4; d++) {
                for (int x = g[0] + DIRS[d], y = g[1] + DIRS[d + 1];
                     x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != 1;
                     x += DIRS[d], y += DIRS[d + 1]
                ) {
                    grid[x][y] = 100;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res += grid[i][j] == 0 ? 1 : 0;
            }
        }
        return res;
    }
}
