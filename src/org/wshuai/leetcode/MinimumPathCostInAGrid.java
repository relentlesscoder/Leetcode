package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/07/2023.
 * #2304 https://leetcode.com/problems/minimum-path-cost-in-a-grid/
 */
public class MinimumPathCostInAGrid {

    // time O(m * n^2), space O(n)
    public int minPathCostDPWithArray(int[][] grid, int[][] moveCost) {
        // 空间优化版 DP
        int res = Integer.MAX_VALUE, m = grid.length, n = grid[0].length;
        int[] pre = new int[n];
        Arrays.setAll(pre, i -> grid[m - 1][i]);
        for (int i = m - 2; i >= 0; i--) {
            int[] dp = new int[n];
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE, v = grid[i][j];
                for (int c = 0; c < n; c++) {
                    min = Math.min(min, moveCost[v][c] + pre[c]);
                }
                dp[j] = min + v;
            }
            pre = dp;
        }
        for (int i = 0; i < n; i++) {
            res = Math.min(res, pre[i]);
        }
        return res;
    }

    // time O(m * n^2), space O(m * n)
    public int minPathCostDPWithGrid(int[][] grid, int[][] moveCost) {
        // 把记忆化搜素翻译成 DP
        int res = Integer.MAX_VALUE, m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        Arrays.setAll(dp[m - 1], i -> grid[m - 1][i]);
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE, v = grid[i][j];
                for (int c = 0; c < n; c++) {
                    min = Math.min(min, moveCost[v][c] + dp[i + 1][c]);
                }
                dp[i][j] = min + v;
            }
        }
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp[0][i]);
        }
        return res;
    }

    // time O(m * n^2), space O(m * n)
    public int minPathCostDFSWithMemorization(int[][] grid, int[][] moveCost) {
        int res = Integer.MAX_VALUE, m = grid.length, n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dfs(0, i, grid, moveCost, memo));
        }
        return res;
    }

    private int dfs(int i, int j, int[][] grid, int[][] cost, int[][] memo) {
        int m = grid.length, n = grid[0].length;
        if (i == m - 1) { // 到达最后一行直接返回格子的值
            return grid[i][j];
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int res = Integer.MAX_VALUE, v = grid[i][j];
        // 找到从当前格子到下一行所有格子的最小路径和
        for (int c = 0; c < n; c++) {
            res = Math.min(res, cost[v][c] + dfs(i + 1, c, grid, cost, memo));
        }
        return memo[i][j] = res + v;
    }
}
