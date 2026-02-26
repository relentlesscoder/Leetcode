package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 02/24/2026.
 * #2435 https://leetcode.com/problems/paths-in-matrix-whose-sum-is-divisible-by-k/
 */
public class PathsInMatrixWhoseSumIsDivisibleByK {

    private static final int MOD = (int) 1e9 + 7;

    // time O(m * n * k), space O(n * k)
    public int numberOfPathsDP(int[][] grid, int k) {
        // 空间优化版 DP
        int m = grid.length, n = grid[0].length;
        int[][] pre = new int[n + 1][k];
        pre[n - 1][0] = 1;
        for (int i = m - 1; i >= 0; i--) {
            int[][] dp = new int[n + 1][k];
            for (int j = n - 1; j >= 0; j--) {
                for (int r = 0; r < k; r++) {
                    int x = (r + grid[i][j]) % k;
                    dp[j][r] = (pre[j][x] + dp[j + 1][x]) % MOD;
                }
            }
            pre = dp;
        }
        return pre[0][0];
    }

    // time O(m * n * k), space O(m * n * k)
    public int numberOfPathsDPWithGrid(int[][] grid, int k) {
        // 将记忆化搜索翻译成 DP
        int m = grid.length, n = grid[0].length;
        int[][][] dp = new int[m + 1][n + 1][k];
        dp[m][n - 1][0] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                for (int r = 0; r < k; r++) {
                    int x = (r + grid[i][j]) % k;
                    dp[i][j][r] = (dp[i + 1][j][x] + dp[i][j + 1][x]) % MOD;
                }
            }
        }
        return dp[0][0][0];
    }

    // time O(m * n * k), space O(m * n * k)
    public int numberOfPathsDFSWithMemorization(int[][] grid, int k) {
        // 记忆化搜索
        int m = grid.length, n = grid[0].length;
        int[][][] memo = new int[m][n][k];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return dfs(0, 0, 0, grid, k, memo);
    }

    private int dfs(int i, int j, int r, int[][] grid, int k, int[][][] memo) {
        int m = grid.length, n = grid[0].length;
        if (i == m - 1 && j == n - 1) {
            return (grid[i][j] + r) % k == 0 ? 1 : 0;
        }
        if (i == m || j == n) {
            return 0;
        }
        if (memo[i][j][r] != -1) {
            return memo[i][j][r];
        }
        int x = (r + grid[i][j]) % k; // 加上当前格子的值然后取余确保值一直在 [0, k - 1] 之内
        // 向下和向右的路径数量和
        return memo[i][j][r] = (dfs(i + 1, j, x, grid, k, memo)
                + dfs(i, j + 1, x, grid, k, memo)) % MOD;
    }
}
