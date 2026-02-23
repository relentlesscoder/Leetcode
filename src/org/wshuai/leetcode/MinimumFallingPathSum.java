package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/30/2019.
 * #0931 https://leetcode.com/problems/minimum-falling-path-sum/
 */
public class MinimumFallingPathSum {

    // time O(n^2), space O(n)
    public int minFallingPathSumDPWithArray(int[][] matrix) {
        // 空间优化版 DP
        int res = Integer.MAX_VALUE, n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        }
        int[] pre = new int[n + 2];
        Arrays.setAll(pre, i -> i >= 1 && i <= n ? matrix[0][i - 1] : Integer.MAX_VALUE);
        for (int i = 1; i < n; i++) {
            pre[0] = pre[n + 1] = Integer.MAX_VALUE;
            int[] dp = new int[n + 2];
            for (int j = 1; j <= n; j++) {
                dp[j] = matrix[i][j - 1] + Math.min(pre[j], Math.min(pre[j - 1], pre[j + 1]));
            }
            pre = dp;
        }
        for (int i = 1; i <= n; i++) {
            res = Math.min(res, pre[i]);
        }
        return res;
    }

    // time O(n^2), space O(n^2)
    public int minFallingPathSumDPWithGrid(int[][] matrix) {
        // 把记忆化搜素翻译成 DP
        int res = Integer.MAX_VALUE, n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        }
        int[][] dp = new int[n][n + 2]; // 为了方便计算，将 dp 的前后各增加一个格子
        // 将 dp 第一行设为网格第一行的值
        Arrays.setAll(dp[0], i -> i >= 1 && i <= n ? matrix[0][i - 1] : Integer.MAX_VALUE);
        for (int i = 1; i < n; i++) {
            dp[i - 1][0] = dp[i - 1][n + 1] = Integer.MAX_VALUE;
            for (int j = 1; j <= n; j++) {
                dp[i][j] = matrix[i][j - 1] + Math.min(dp[i - 1][j],
                        Math.min(dp[i - 1][j - 1], dp[i - 1][j + 1]));
            }
        }
        for (int i = 1; i <= n; i++) {
            res = Math.min(res, dp[n - 1][i]);
        }
        return res;
    }

    // time O(n^2), space O(n^2)
    public int minFallingPathSumDFSWithMemorization(int[][] matrix) {
        int res = Integer.MAX_VALUE, n = matrix.length;
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dfs(n - 1, i, matrix, memo));
        }
        return res;
    }

    private int dfs(int i, int j, int[][] matrix, int[][] memo) {
        int n = matrix.length;
        if (j < 0 || j >= n) {
            return Integer.MAX_VALUE;
        }
        if (i == 0) {
            return matrix[0][j];
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        return memo[i][j] = matrix[i][j] + Math.min(dfs(i - 1, j, matrix, memo),
                Math.min(dfs(i - 1, j - 1, matrix, memo), dfs(i - 1, j + 1, matrix, memo)));
    }
}
