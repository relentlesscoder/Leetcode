package org.wshuai.leetcode;

/**
 * Created by Wei on 11/21/2016.
 * #0463 https://leetcode.com/problems/island-perimeter/
 */
public class IslandPerimeter {

    // time O(m * n), space O(1)
    public int islandPerimeter(int[][] grid) {
        int res = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                if (i == 0 || grid[i - 1][j] == 0) {
                    res++;
                }
                if (i == m - 1 || grid[i + 1][j] == 0) {
                    res++;
                }
                if (j == 0 || grid[i][j - 1] == 0) {
                    res++;
                }
                if (j == n - 1 || grid[i][j + 1] == 0) {
                    res++;
                }
            }
        }
        return res;
    }
}
