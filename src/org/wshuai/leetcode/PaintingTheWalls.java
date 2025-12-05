package org.wshuai.leetcode;

/**
 * Created by Wei on 12/10/2023.
 * #2742 https://leetcode.com/problems/painting-the-walls/
 */
public class PaintingTheWalls {

    private static final int MAX = 1_000_000_000;

    // time O(n^2), space O(n)
    public int paintWallsBottomUpWithSpaceOptimization(int[] cost, int[] time) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = MAX;
        }
        for (int i = n - 1; i >= 0; i--) {
            int[] next = new int[n + 1];
            for (int remain = 1; remain <= n; remain++) {
                int paint = cost[i] + dp[Math.max(0, remain - 1 - time[i])];
                int dontPaint = dp[remain];
                next[remain] = Math.min(paint, dontPaint);
            }
            dp = next;
        }
        return dp[n];
    }

    // time O(n^2), space O(n^2)
    public int paintWallsBottomUp(int[] cost, int[] time) {
        int n = cost.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[n][i] = MAX;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int remain = 1; remain <= n; remain++) {
                int paint = cost[i] + dp[i + 1][Math.max(0, remain - 1 - time[i])];
                int dontPaint = dp[i + 1][remain];
                dp[i][remain] = Math.min(paint, dontPaint);
            }
        }
        return dp[0][n];
    }

    // time O(n^2), space O(n^2)
    public int paintWallsTopDown(int[] cost, int[] time) {
        int n = cost.length;
        int[][] dp = new int[n][n + 1];
        return dfs(0, n, cost, time, dp);
    }

    private int dfs(int i, int remain, int[] cost, int[] time, int[][] dp) {
        if (remain <= 0) {
            return 0;
        }
        if (i == cost.length) {
            return MAX;
        }
        if (dp[i][remain] != 0) {
            return dp[i][remain];
        }
        int paint = cost[i] + dfs(i + 1, remain - 1 - time[i], cost, time, dp);
        int dontPaint = dfs(i + 1, remain, cost, time, dp);
        dp[i][remain] = Math.min(paint, dontPaint);
        return dp[i][remain];
    }
}
