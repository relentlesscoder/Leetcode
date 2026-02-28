package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 05/31/2020.
 * #1463 https://leetcode.com/problems/cherry-pickup-ii/
 */
public class CherryPickupII {
    private static final int MIN = -(int) 1e6;

    // time O(m * n^2), space O(n^2)
    public int cherryPickupDP(int[][] grid) {
        // 空间优化版 DP
        int m = grid.length, n = grid[0].length;
        int[][] pre = new int[n + 2][n + 2];
        for (int[] row : pre) {
            Arrays.fill(row, MIN);
        }
        for (int c1 = 0; c1 < n; c1++) {
            for (int c2 = 0; c2 < n; c2++) {
                pre[c1 + 1][c2 + 1] = grid[m - 1][c1] + (c1 == c2 ? 0 : grid[m - 1][c2]);
            }
        }
        for (int r = m - 2; r >= 0; r--) {
            int[][] dp = new int[n + 2][n + 2];
            for (int[] row : dp) {
                Arrays.fill(row, MIN);
            }
            for (int c1 = 1; c1 <= n; c1++) {
                for (int c2 = 1; c2 <= n; c2++) {
                    int val = grid[r][c1 - 1] + (c1 == c2 ? 0 : grid[r][c2 - 1]);
                    dp[c1][c2] = val + Math.max(pre[c1 - 1][c2 - 1], Math.max(
                            Math.max(Math.max(pre[c1 - 1][c2], pre[c1 - 1][c2 + 1]),
                                    Math.max(pre[c1][c2 - 1], pre[c1][c2])),
                            Math.max(Math.max(pre[c1][c2 + 1], pre[c1 + 1][c2 - 1]),
                                    Math.max(pre[c1 + 1][c2], pre[c1 + 1][c2 + 1]))));
                }
            }
            pre = dp;
        }
        return pre[1][n];
    }

    // time O(m * n^2), space O(m * n^2)
    public int cherryPickupDPWithGrid(int[][] grid) {
        // 把记忆化搜索翻译成 DP
        int m = grid.length, n = grid[0].length;
        int[][][] dp = new int[m][n + 2][n + 2];
        for (int[][] matrix : dp) {
            for (int[] row : matrix) {
                Arrays.fill(row, MIN);
            }
        }
        for (int c1 = 0; c1 < n; c1++) {
            for (int c2 = 0; c2 < n; c2++) {
                dp[m - 1][c1 + 1][c2 + 1] = grid[m - 1][c1] + (c1 == c2 ? 0 : grid[m - 1][c2]);
            }
        }
        for (int r = m - 2; r >= 0; r--) {
            for (int c1 = 1; c1 <= n; c1++) {
                for (int c2 = 1; c2 <= n; c2++) {
                    int val = grid[r][c1 - 1] + (c1 == c2 ? 0 : grid[r][c2 - 1]);
                    dp[r][c1][c2] = val + Math.max(dp[r + 1][c1 - 1][c2 - 1], Math.max(
                            Math.max(Math.max(dp[r + 1][c1 - 1][c2], dp[r + 1][c1 - 1][c2 + 1]),
                                    Math.max(dp[r + 1][c1][c2 - 1], dp[r + 1][c1][c2])),
                            Math.max(Math.max(dp[r + 1][c1][c2 + 1], dp[r + 1][c1 + 1][c2 - 1]),
                                    Math.max(dp[r + 1][c1 + 1][c2], dp[r + 1][c1 + 1][c2 + 1]))));
                }
            }
        }
        return dp[0][1][n];
    }

    // time O(m * n^2), space O(m * n^2)
    public int cherryPickupDFSWithMemorization(int[][] grid) {
        // 记忆化搜索
        int m = grid.length, n = grid[0].length;
        int[][][] memo = new int[m][n][n];
        for (int[][] matrix : memo) {
            for (int[] arr : matrix) {
                Arrays.fill(arr, -1);
            }
        }
        return dfs(0, 0, n - 1, grid, memo);
    }

    private int dfs(int r, int c1, int c2, int[][] grid, int[][][] memo) {
        int m = grid.length, n = grid[0].length;
        // 越界
        if (c1 < 0 || c1 >= n || c2 < 0 || c2 >= n) {
            return MIN;
        }
        int val = grid[r][c1] + (c1 == c2 ? 0 : grid[r][c2]);
        // 边界条件: 到达最后一行
        if (r == m - 1) {
            return val;
        }
        if (memo[r][c1][c2] != -1) {
            return memo[r][c1][c2];
        }
        // 两个机器人移动的可能组合: 左左，左中，左右，中左，中中，中右，右左，右中，右右
        return memo[r][c1][c2] = val + Math.max(dfs(r + 1, c1 - 1, c2 - 1, grid, memo), Math.max(
                Math.max(Math.max(dfs(r + 1, c1 - 1, c2, grid, memo), dfs(r + 1, c1 - 1, c2 + 1, grid, memo)),
                        Math.max(dfs(r + 1, c1, c2 - 1, grid, memo), dfs(r + 1, c1, c2, grid, memo))),
                Math.max(Math.max(dfs(r + 1, c1, c2 + 1, grid, memo), dfs(r + 1, c1 + 1, c2 - 1, grid, memo)),
                        Math.max(dfs(r + 1, c1 + 1, c2, grid, memo), dfs(r + 1, c1 + 1, c2 + 1, grid, memo)))));
    }
}
