package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 02/21/2026.
 * #3742 https://leetcode.com/problems/maximum-path-score-in-a-grid/
 */
public class MaximumPathScoreInAGrid {

    // time O(m * n * k), space O(n * k)
    public int maxPathScoreDPWithArray(int[][] grid, int k) {
        // 空间优化版 DP
        int m = grid.length, n = grid[0].length;
        int[][] pre = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(pre[i], Integer.MIN_VALUE / 2);
        }
        Arrays.fill(pre[n - 1], 0);
        for (int i = m - 1; i >= 0; i--) {
            int[][] dp = new int[n + 1][k + 1];
            Arrays.fill(dp[n], Integer.MIN_VALUE / 2);
            for (int j = n - 1; j >= 0; j--) {
                int c = grid[i][j] > 0 ? 1 : 0;
                for (int x = 0; x <= k; x++) {
                    dp[j][x] = Integer.MIN_VALUE / 2;
                    if (x < c) {
                        continue;
                    }
                    dp[j][x] = grid[i][j] + Math.max(pre[j][x - c], dp[j + 1][x - c]);
                }
            }
            pre = dp;
        }
        return pre[0][k] < 0 ? -1 : pre[0][k];
    }

    // time O(m * n * k), space O(m * n * k)
    public int maxPathScoreDPWithGrid(int[][] grid, int k) {
        // 把记忆化搜索翻译成 DP
        int m = grid.length, n = grid[0].length;
        // 多加一行一列处理出界的情况以便于计算
        int[][][] dp = new int[m + 1][n + 1][k + 1];
        // 将最后一行设为出界
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[m][i], Integer.MIN_VALUE / 2);
        }
        // 将 dp[m - 1, n - 1] 下面 (或者右边) 的格子预设为 0 便于计算
        Arrays.fill(dp[m][n - 1], 0);
        for (int i = m - 1; i >= 0; i--) {
            // 将每一行的最后一列的方格设为出界
            Arrays.fill(dp[i][n], Integer.MIN_VALUE / 2);
            for (int j = n - 1; j >= 0; j--) {
                // 翻译记忆化搜索中的逻辑
                int c = grid[i][j] > 0 ? 1 : 0;
                for (int x = 0; x <= k; x++) {
                    dp[i][j][x] = Integer.MIN_VALUE / 2;
                    if (x < c) {
                        continue;
                    }
                    dp[i][j][x] = grid[i][j] +
                            Math.max(dp[i + 1][j][x - c], dp[i][j + 1][x - c]);
                }
            }
        }
        return dp[0][0][k] < 0 ? -1 : dp[0][0][k];
    }

    // time O(m * n * k), space O(m * n * k)
    public int maxPathScoreDFSWithMemorization(int[][] grid, int k) {
        // 记忆化搜索
        int m = grid.length, n = grid[0].length;
        int[][][] memo = new int[m][n][k + 1];
        for (int i = 0; i < m; i++) {
            for (int[] d : memo[i]) {
                Arrays.fill(d, Integer.MIN_VALUE);
            }
        }
        int cost = dfs(0, 0, grid, k, memo);
        return cost < 0 ? -1 : cost;
    }

    private int dfs(int i, int j, int[][] grid, int k, int[][][] memo) {
        int m = grid.length, n = grid[0].length;
        // 越界或者花费超过 k
        if (i == m || j == n || (grid[i][j] > 0 && k == 0)) {
            return Integer.MIN_VALUE / 2;
        }
        if (i == m - 1 && j == n - 1) {
            return grid[i][j];
        }
        if (memo[i][j][k] != Integer.MIN_VALUE) {
            return memo[i][j][k];
        }
        // 花费 c 往右或者下继续走
        int c = grid[i][j] > 0 ? 1 : 0;
        return memo[i][j][k] = grid[i][j] + Math.max(dfs(i + 1, j, grid, k - c, memo),
                dfs(i, j + 1, grid, k - c, memo));
    }
}
