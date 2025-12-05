package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/24/2024.
 * #2328 https://leetcode.com/problems/number-of-increasing-paths-in-a-grid/
 */
public class NumberOfIncreasingPathsInAGrid {

    private int[] dirs = new int[]{-1, 0, 1, 0, -1};
    private int mod = (int)1e9 + 7;

    // time O(m * n), space O(m * n)
    public int countPaths(int[][] grid) {
        int res = 0, m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = (res + dfs(grid, i, j, m, n, dp)) % mod;
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j, int m, int n, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int res = 1;
        for (int k = 0; k < 4; k++) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] < grid[i][j]) {
                res = (res + dfs(grid, x, y, m, n, dp)) % mod;
            }
        }
        return dp[i][j] = res;
    }
}
