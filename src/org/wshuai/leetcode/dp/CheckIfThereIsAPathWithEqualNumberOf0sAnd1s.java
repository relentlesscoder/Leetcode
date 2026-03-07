package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 02/26/2026.
 * #2510 https://leetcode.com/problems/check-if-there-is-a-path-with-equal-number-of-0s-and-1s/
 */
public class CheckIfThereIsAPathWithEqualNumberOf0sAnd1s {

    // time O(m * n * (m + n)), space O(n * (m + n))
    public boolean isThereAPathDP(int[][] grid) {
        // 空间优化版 DP
        int m = grid.length, n = grid[0].length;
        if ((m + n - 1) % 2 == 1) {
            return false;
        }
        boolean[][] pre = new boolean[n + 1][m + n];
        pre[n - 1][(m + n - 1) / 2] = true;
        for (int i = m - 1; i >= 0; i--) {
            boolean[][] dp = new boolean[n + 1][m + n];
            for (int j = n - 1; j >= 0; j--) {
                for (int k = 0; k + grid[i][j] < m + n; k++) {
                    dp[j][k] = pre[j][k + grid[i][j]]
                            || dp[j + 1][k + grid[i][j]];
                }
            }
            pre = dp;
        }
        return pre[0][0];
    }

    // time O(m * n * (m + n)), space O(m * n * (m + n))
    public boolean isThereAPathDPWithGrid(int[][] grid) {
        // 把记忆化搜索翻译成 DP
        int m = grid.length, n = grid[0].length;
        if ((m + n - 1) % 2 == 1) {
            return false;
        }
        boolean[][][] dp = new boolean[m + 1][n + 1][m + n];
        dp[m][n - 1][(m + n - 1) / 2] = true;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                for (int k = 0; k + grid[i][j] < m + n; k++) {
                    dp[i][j][k] = dp[i + 1][j][k + grid[i][j]]
                            || dp[i][j + 1][k + grid[i][j]];
                }
            }
        }
        return dp[0][0][0];
    }

    // time O(m * n * (m + n)), space O(m * n * (m + n))
    public boolean isThereAPathDFSWithMemorization(int[][] grid) {
        // 记忆化搜索
        int m = grid.length, n = grid[0].length;
        if ((m + n - 1) % 2 == 1) {
            return false;
        }
        Boolean[][][] memo = new Boolean[m][n][m + n];
        return dfs(0, 0, 0, grid, memo);
    }

    private boolean dfs(int i, int j, int s, int[][] grid, Boolean[][][] memo) {
        int m = grid.length, n = grid[0].length;
        if (i == m - 1 && j == n - 1) {
            return (s + grid[i][j]) == (m + n - 1) / 2;
        }
        if (i == m || j == n) {
            return false;
        }
        if (memo[i][j][s] != null) {
            return memo[i][j][s];
        }
        return memo[i][j][s] = dfs(i + 1, j, s + grid[i][j], grid, memo)
                || dfs(i, j + 1, s + grid[i][j], grid, memo);
    }
}
