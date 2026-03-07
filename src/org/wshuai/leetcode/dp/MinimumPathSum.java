package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 10/06/2016.
 * #0064 https://leetcode.com/problems/minimum-path-sum/
 */
public class MinimumPathSum {

    // time O(m * n), space O(n)
    public int minPathSumDP(int[][] grid) {
        // 空间优化版 DP
        int m = grid.length, n = grid[0].length;
        int[] pre = new int[n + 1];
        Arrays.fill(pre, Integer.MAX_VALUE);
        pre[1] = 0;
        for (int i = 1; i <= m; i++) {
            int[] dp = new int[n + 1];
            dp[0] = Integer.MAX_VALUE;
            for (int j = 1; j <= n; j++) {
                dp[j] = Math.min(pre[j], dp[j - 1]) + grid[i - 1][j - 1];
            }
            pre = dp;
        }
        return pre[n];
    }

    // time O(m * n), space O(m * n)
    public int minPathSumDPWithGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][1] = 0;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return dp[m][n];
    }

    // time O(m * n), space O(m * n)
    public int minPathSumDFSWithMemorization(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(grid.length - 1, grid[0].length - 1, grid, memo);
    }

    private int dfs(int i, int j, int[][] grid, int[][] memo) {
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        if (i == 0 && j == 0) {
            return grid[i][j];
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // 到达 grid[i][j] 的最小路径为从左 (i, j - 1) 或者从右来 (i - 1, j) 的路径
        // 的较小值加上 grid[i][j], 因此可以分解为更小的子问题。
        return memo[i][j] = Math.min(dfs(i - 1, j, grid, memo),
                dfs(i, j - 1, grid, memo)) + grid[i][j];
    }
}
