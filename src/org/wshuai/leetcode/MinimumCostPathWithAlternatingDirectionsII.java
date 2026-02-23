package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 02/20/2026.
 * #3603 https://leetcode.com/problems/minimum-cost-path-with-alternating-directions-ii/
 */
public class MinimumCostPathWithAlternatingDirectionsII {

    // time O(m * n), space O(n)
    public long minCostDPWithArray(int m, int n, int[][] waitCost) {
        // 空间优化版 DP
        long[] pre = new long[n + 1];
        Arrays.fill(pre, Long.MAX_VALUE);
        pre[1] = -waitCost[0][0];
        for (int i = 1; i <= m; i++) {
            long[] dp = new long[n + 1];
            dp[0] = Long.MAX_VALUE;
            for (int j = 1; j <= n; j++) {
                long cost = (long) i * j + waitCost[i - 1][j - 1];
                dp[j] = Math.min(pre[j], dp[j - 1]) + cost;
            }
            pre = dp;
        }
        return pre[n] - waitCost[m - 1][n - 1];
    }

    // time O(m * n), space O(m * n)
    public long minCostDPWithGrid(int m, int n, int[][] waitCost) {
        // 把记忆化搜索翻译成 DP
        long[][] dp = new long[m + 1][n + 1];
        Arrays.fill(dp[0], Long.MAX_VALUE);
        dp[0][1] = -waitCost[0][0];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = Long.MAX_VALUE;
            for (int j = 1; j <= n; j++) {
                long cost = (long) i * j + waitCost[i - 1][j - 1];
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + cost;
            }
        }
        return dp[m][n] - waitCost[m - 1][n - 1];
    }

    // time O(m * n), space O(m * n)
    public long minCostDFSWithMemorization(int m, int n, int[][] waitCost) {
        // 记忆化搜索
        // 第一个方格和最后一个方格无需等待
        long[][] memo = new long[m][n];
        return dfs(m - 1, n - 1, waitCost, memo) - waitCost[m - 1][n - 1];
    }

    private long dfs(int i, int j, int[][] waitCost, long[][] memo) {
        if (i == 0 && j == 0) {
            return 1L;
        }
        if (i < 0 || j < 0) {
            return Long.MAX_VALUE;
        }
        if (memo[i][j] != 0L) {
            return memo[i][j];
        }
        long cost = (long) (i + 1) * (j + 1) + waitCost[i][j];
        return cost + Math.min(dfs(i - 1, j, waitCost, memo), dfs(i, j - 1, waitCost, memo));
    }
}
