package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 03/03/2026.
 * #3489 https://leetcode.com/problems/zero-array-transformation-iv/
 */
public class ZeroArrayTransformationIV {

    // time O(n * m * MAX), space O(MAX)
    public int minZeroArrayDP(int[] nums, int[][] queries) {
        // 空间优化版 DP
        int res = 0, n = nums.length, m = queries.length;
        for (int x = 0; x < n; x++) {
            int[] dp = new int[nums[x] + 1];
            Arrays.fill(dp, 10_000);
            dp[0] = m;
            for (int i = m - 1; i >= 0; i--) {
                int l = queries[i][0], r = queries[i][1], v = queries[i][2];
                if (l > x || r < x) {
                    dp[0] = i;
                    continue;
                }
                for (int t = nums[x]; t >= v; t--) {
                    dp[t] = Math.min(dp[t], dp[t - v]);
                }
                dp[0] = i;
            }
            res = Math.max(res, dp[nums[x]]);
        }
        return res > m ? -1 : res;
    }

    // time O(n * m * MAX), space O(m * MAX)
    public int minZeroArray(int[] nums, int[][] queries) {
        // 把记忆化搜索翻译成 DP
        int res = 0, n = nums.length, m = queries.length;
        for (int x = 0; x < n; x++) {
            int[][] dp = new int[m + 1][nums[x] + 1];
            Arrays.fill(dp[m], 10_000);
            dp[m][0] = m;
            for (int i = m - 1; i >= 0; i--) {
                dp[i][0] = i;
                for (int t = 1; t <= nums[x]; t++) {
                    int l = queries[i][0], r = queries[i][1], v = queries[i][2];
                    if (l > x || r < x || v > t) {
                        dp[i][t] = dp[i + 1][t];
                    } else {
                        dp[i][t] = Math.min(dp[i + 1][t], dp[i + 1][t - v]);
                    }
                }
            }
            res = Math.max(res, dp[0][nums[x]]);
        }
        return res > m ? -1 : res;
    }

    // time O(n * m * MAX), space O(m * MAX)
    public int minZeroArrayDFSWithMemorization(int[] nums, int[][] queries) {
        // 记忆化搜索 - 0/1 背包。题目可以拆解为对 nums 中每个元素能不能从 queries 的前缀中选一些数字
        // 使得它们的和等于当前元素 - 转化为 0/1 背包问题。
        int res = 0, n = nums.length, m = queries.length;
        for (int i = 0; i < n; i++) {
            int[][] memo = new int[m][nums[i] + 1];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }
            res = Math.max(res, dfs(0, i, nums[i], queries, memo));
        }
        return res > m ? -1 : res;
    }

    private int dfs(int i, int x, int t, int[][] queries, int[][] memo) {
        int n = queries.length;
        if (t == 0) {
            return i;
        }
        if (i == n) {
            return 10_000;
        }
        if (memo[i][t] != -1) {
            return memo[i][t];
        }
        int l = queries[i][0], r = queries[i][1], v = queries[i][2];
        if (l > x || r < x || v > t) { // 不能选 i
            return memo[i][t] = dfs(i + 1, x, t, queries, memo);
        } else { // 选或者不选
            return memo[i][t] = Math.min(dfs(i + 1, x, t, queries, memo),
                    dfs(i + 1, x, t - v, queries, memo));
        }
    }
}
