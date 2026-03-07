package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 09/20/2020.
 * #1594 https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/
 */
public class MaximumNonNegativeProductInAMatrix {

    private static final int MOD = (int) 1e9 + 7;

    // time O(m * n), space O(n)
    public int maxProductPathDPWithArray(int[][] grid) {
        // 空间优化版 DP
        int m = grid.length, n = grid[0].length;
        long[][] pre = new long[n + 1][2];
        for (int i = 0; i <= n; i++) {
            pre[i][0] = Long.MAX_VALUE;
            pre[i][1] = Long.MIN_VALUE;
        }
        pre[n - 1][0] = pre[n - 1][1] = 1L;
        for (int i = m - 1; i >= 0; i--) {
            long[][] dp = new long[n + 1][2];
            dp[n][0] = Long.MAX_VALUE;
            dp[n][1] = Long.MIN_VALUE;
            for (int j = n - 1; j >= 0; j--) {
                dp[j] = new long[] {Long.MAX_VALUE, Long.MIN_VALUE};
                long[] down = pre[j];
                long[] right = dp[j + 1];
                if (down[0] != Long.MAX_VALUE) {
                    long p1 = grid[i][j] * down[0];
                    long p2 = grid[i][j] * down[1];
                    dp[j][0] = Math.min(dp[j][0], Math.min(p1, p2));
                    dp[j][1] = Math.max(dp[j][1], Math.max(p1, p2));
                }
                if (right[0] != Long.MAX_VALUE) {
                    long p1 = grid[i][j] * right[0];
                    long p2 = grid[i][j] * right[1];
                    dp[j][0] = Math.min(dp[j][0], Math.min(p1, p2));
                    dp[j][1] = Math.max(dp[j][1], Math.max(p1, p2));
                }
            }
            pre = dp;
        }
        return Math.max(-1, (int) (pre[0][1] % MOD));
    }

    // time O(m * n), space O(m * n)
    public int maxProductPathDPWithGrid(int[][] grid) {
        // 把记忆化搜索翻译成 DP
        int m = grid.length, n = grid[0].length;
        long[][][] dp = new long[m + 1][n + 1][2];
        for (int i = 0; i <= n; i++) {
            dp[m][i][0] = Long.MAX_VALUE;
            dp[m][i][1] = Long.MIN_VALUE;
        }
        dp[m][n - 1][0] = dp[m][n - 1][1] = 1L;
        for (int i = m - 1; i >= 0; i--) {
            dp[i][n][0] = Long.MAX_VALUE;
            dp[i][n][1] = Long.MIN_VALUE;
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = new long[]{Long.MAX_VALUE, Long.MIN_VALUE};
                long[] down = dp[i + 1][j];
                long[] right = dp[i][j + 1];
                if (down[0] != Long.MAX_VALUE) {
                    long p1 = grid[i][j] * down[0];
                    long p2 = grid[i][j] * down[1];
                    dp[i][j][0] = Math.min(dp[i][j][0], Math.min(p1, p2));
                    dp[i][j][1] = Math.max(dp[i][j][1], Math.max(p1, p2));
                }
                if (right[0] != Long.MAX_VALUE) {
                    long p1 = grid[i][j] * right[0];
                    long p2 = grid[i][j] * right[1];
                    dp[i][j][0] = Math.min(dp[i][j][0], Math.min(p1, p2));
                    dp[i][j][1] = Math.max(dp[i][j][1], Math.max(p1, p2));
                }
            }
        }
        return Math.max(-1, (int) (dp[0][0][1] % MOD));
    }

    // time O(m * n), space O(m * n)
    public int maxProductPathDFSWithMemorization(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][][] memo = new long[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j][0] = Long.MAX_VALUE;
                memo[i][j][1] = Long.MIN_VALUE;
            }
        }
        return Math.max(-1, (int) (dfs(0, 0, grid, memo)[1] % MOD));
    }

    private long[] dfs(int i, int j, int[][] grid, long[][][] memo) {
        int m = grid.length, n = grid[0].length;
        if (i == m - 1 && j == n - 1) {
            return new long[]{grid[i][j], grid[i][j]};
        }
        if (i == m || j == n) {
            return new long[]{Long.MAX_VALUE, Long.MIN_VALUE};
        }
        if (memo[i][j][0] != Long.MAX_VALUE) {
            return memo[i][j];
        }
        long[] res = new long[]{Long.MAX_VALUE, Long.MIN_VALUE};
        long[] down = dfs(i + 1, j, grid, memo);
        long[] right = dfs(i, j + 1, grid, memo);
        // 因为格子的值有可能为负数所以必须同时维护最大值和最小值
        if (down[0] != Long.MAX_VALUE) {
            long p1 = grid[i][j] * down[0];
            long p2 = grid[i][j] * down[1];
            res[0] = Math.min(p1, p2);
            res[1] = Math.max(p1, p2);
        }
        if (right[0] != Long.MAX_VALUE) {
            long p1 = grid[i][j] * right[0];
            long p2 = grid[i][j] * right[1];
            res[0] = Math.min(res[0], Math.min(p1, p2));
            res[1] = Math.max(res[1], Math.max(p1, p2));
        }
        return res;
    }
}
