package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 02/20/2026.
 * #3393 https://leetcode.com/problems/count-paths-with-the-given-xor-value/
 */
public class CountPathsWithTheGivenXORValue {

    private static final int MOD = (int) 1e9 + 7;

    // time O(m * n), space O(m * n)
    public int countPathsWithXorValueDPWithGrid(int[][] grid, int k) {
        // 把记忆化搜索翻译成 DP
        int m = grid.length, n = grid[0].length, max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, grid[i][j]);
            }
        }
        int u = 1 << (32 - Integer.numberOfLeadingZeros(max));
        if (k >= u) {
            return 0;
        }
        int[][][] dp = new int[m + 1][n + 1][u];
        dp[0][1][0] = 1; // 初始化让 dp[1][1] 上面 (也可以是左边) 异或和为 0 的格子为 1
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int x = 0; x < u; x++) {
                    dp[i][j][x] = (dp[i - 1][j][x ^ grid[i - 1][j - 1]] % MOD
                            + dp[i][j - 1][x ^ grid[i - 1][j - 1]]) % MOD;
                }
            }
        }
        return dp[m][n][k];
    }

    // time O(m * n), space O(m * n)
    public int countPathsWithXorValueDFSWithMemorization(int[][] grid, int k) {
        // 记忆化搜索
        // 利用异或和的性质 a ^ b = c 则 a ^ c = b
        //   1 0 0 1 0
        // ^ 0 1 0 1 1
        // = 1 1 0 0 1

        //   1 1 0 0 1
        // ^ 0 1 0 1 1
        // = 1 0 0 1 0
        int m = grid.length, n = grid[0].length, max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, grid[i][j]);
            }
        }
        int u = 1 << (32 - Integer.numberOfLeadingZeros(max));
        if (k >= u) {
            return 0;
        }
        int[][][] memo = new int[m][n][u];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return dfs(m - 1, n - 1, k, grid, memo);
    }

    private int dfs(int i, int j, int k, int[][] grid, int[][][] memo) {
        if (i == 0 && j == 0) {
            return grid[i][j] == k ? 1 : 0;
        }
        if (i < 0 || j < 0) {
            return 0;
        }
        if (memo[i][j][k] != -1) {
            return memo[i][j][k];
        }
        // 根据以上异或和的性质，将问题分解为从上边或者左边格子过来的子问题:
        // f(i, j, k) = f(i - 1, j, k ^ grid[i, j]) + f(i, j - 1, k ^ grid[i, j])
        return memo[i][j][k] = (dfs(i - 1, j, k ^ grid[i][j], grid, memo) % MOD
                + dfs(i, j - 1, k ^ grid[i][j], grid, memo)) % MOD;
    }
}
