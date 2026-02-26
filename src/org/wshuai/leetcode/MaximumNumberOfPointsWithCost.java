package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/23/2023.
 * #1937 https://leetcode.com/problems/maximum-number-of-points-with-cost/
 */
public class MaximumNumberOfPointsWithCost {

    // time O(m * n), space O(n)
    public long maxPointsDPWithArray(int[][] points) {
        // 进一步优化，将 DP 由二维降到一维
        long res = 0;
        int m = points.length, n = points[0].length;
        long[] pre = new long[n];
        Arrays.setAll(pre, i -> points[0][i]);
        for (int i = 1; i < m; i++) {
            long left = 0L;
            long[] right = new long[n], dp = new long[n];
            right[n - 1] = pre[n - 1] - n + 1;
            for (int j = n - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1], pre[j] - j);
            }
            for (int j = 0; j < n; j++) {
                left = Math.max(left, pre[j] + j);
                dp[j] = j == n - 1 ? points[i][j] - j + left :
                        Math.max(points[i][j] - j + left, points[i][j] + j + right[j + 1]);
            }
            pre = dp;
        }
        for (int i = 0; i < n; i++) {
            res = Math.max(res, pre[i]);
        }
        return res;
    }

    // time O(m * n), space O(m * n)
    public long maxPointsDPWithGrid(int[][] points) {
        // 优化: 在处理 DP[i][j] 时同步计算 left 的值
        long res = 0;
        int m = points.length, n = points[0].length;
        long[][] dp = new long[m][n];
        Arrays.setAll(dp[0], i -> points[0][i]);
        for (int i = 1; i < m; i++) {
            long left = 0L;
            long[] right = new long[n];
            right[n - 1] = dp[i - 1][n - 1] - n + 1;
            for (int j = n - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1], dp[i - 1][j] - j);
            }
            for (int j = 0; j < n; j++) {
                left = Math.max(left, dp[i - 1][j] + j);
                dp[i][j] = j == n - 1 ? points[i][j] - j + left :
                        Math.max(points[i][j] - j + left, points[i][j] + j + right[j + 1]);
            }
        }
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[m - 1][i]);
        }
        return res;
    }

    // time O(m * n), space O(m * n)
    public long maxPointsDPWithGridVerbose(int[][] points) {
        // 根据记忆化搜索，我们可以得到公式
        // dp[i][j] = points[i][j] + max(dp[i - 1][k] - |k - j|)
        // 分类讨论去掉绝对值号
        // -> dp[i][j] = p[i][j] - j + max(dp[i - 1][k] + k), k <= j
        // -> dp[i][j] = p[i][j] + j + max(dp[i - 1][k] - k), k > j
        // 这样要计算 dp[i][j] 我们需要得到 j 左边的 max(dp[i - 1][k] + k) 和
        // j 右边的 max(dp[i - 1][k] - k) 。
        long res = 0;
        int m = points.length, n = points[0].length;
        long[][] dp = new long[m][n];
        Arrays.setAll(dp[0], i -> points[0][i]);
        for (int i = 1; i < m; i++) {
            // 根据公式计算 j 左边或者右边的最大值
            long[] left = new long[n], right = new long[n];
            left[0] = dp[i - 1][0];
            right[n - 1] = dp[i - 1][n - 1] - n + 1;
            // 计算 j 左边的 max(dp[i - 1][k] + k)
            for (int j = 1; j < n; j++) {
                left[j] = Math.max(left[j - 1], dp[i - 1][j] + j);
            }
            // 计算 j 右边的 max(dp[i - 1][k] - k)
            for (int j = n - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1], dp[i - 1][j] - j);
            }
            // 合并
            dp[i][n - 1] = points[i][n - 1] - n + 1 + left[n - 1];
            for (int j = 0; j < n - 1; j++) {
                dp[i][j] = Math.max(points[i][j] - j + left[j], points[i][j] + j + right[j + 1]);
            }
        }
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[m - 1][i]);
        }
        return res;
    }

    // time O(m * n^2), space O(m * n)
    public long maxPointsDFSWithMemorization(int[][] points) {
        // 记忆化搜索
        long res = 0;
        int m = points.length, n = points[0].length;
        long[][] memo = new long[m][n];
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dfs(m - 1, i, points, memo));
        }
        return res;
    }

    private long dfs(int i, int j, int[][] points, long[][] memo) {
        int n = points[0].length;
        if (i == 0) {
            return points[i][j];
        }
        if (memo[i][j] > 0L) {
            return memo[i][j];
        }
        long res = 0L;
        // 记忆化搜索需要 O(n) 时间还需要优化
        for (int k = 0; k < n; k++) {
            res = Math.max(res, points[i][j] - Math.abs(j - k) + dfs(i - 1, k, points, memo));
        }
        return memo[i][j] = res;
    }
}
