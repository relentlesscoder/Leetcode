package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 03/02/2026.
 * #3180 https://leetcode.com/problems/maximum-total-reward-using-operations-i/
 */
public class MaximumTotalRewardUsingOperationsI {

    // time O(n * m), space O(m)
    public int maxTotalRewardDPWithRollingArray(int[] rewardValues) {
        // 空间优化版 DP
        int n = rewardValues.length, m = 0;
        Arrays.sort(rewardValues);
        for (int x : rewardValues) {
            m = Math.max(m, x);
        }
        int[] pre = new int[2 * m + 1];
        for (int i = n - 1; i >= 0; i--) {
            int[] dp = new int[2 * m + 1];
            for (int r = 0; r < 2 * m; r++) {
                if (rewardValues[i] <= r) {
                    dp[r] =pre[r];
                } else {
                    dp[r] = Math.max(pre[r],
                            rewardValues[i] + pre[r + rewardValues[i]]);
                }
            }
            pre = dp;
        }
        return pre[0];
    }

    // time O(n * m), space O(n * m)
    public int maxTotalRewardDPWithGrid(int[] rewardValues) {
        // 把记忆化搜索翻译成 DP
        int n = rewardValues.length, m = 0;
        Arrays.sort(rewardValues);
        for (int x : rewardValues) {
            m = Math.max(m, x);
        }
        int[][] dp = new int[n + 1][2 * m + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int r = 0; r < 2 * m; r++) {
                if (rewardValues[i] <= r) {
                    dp[i][r] = dp[i + 1][r];
                } else {
                    dp[i][r] = Math.max(dp[i + 1][r],
                            rewardValues[i] + dp[i + 1][r + rewardValues[i]]);
                }
            }
        }
        return dp[0][0];
    }

    // time O(n * m), space O(n * m)
    public int maxTotalRewardDFSWithMemorization(int[] rewardValues) {
        // 记忆化搜索 - 0/1 背包
        int n = rewardValues.length, m = 0;
        Arrays.sort(rewardValues);
        for (int x : rewardValues) {
            m = Math.max(m, x);
        }
        // 最大总奖励不会超过 2 * max - 1 - 假设最后选择的元素为 v 则前面的奖励和
        // 必须小于 v 则总奖励为 2 * v - 1 。
        int[][] memo = new int[n][2 * m];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(0, 0, rewardValues, memo);
    }

    private int dfs(int i, int r, int[] nums, int[][] memo) {
        if (i == nums.length) {
            return r;
        }
        if (memo[i][r] != -1) {
            return memo[i][r];
        }
        if (nums[i] <= r) {
            return memo[i][r] = dfs(i + 1, r, nums, memo);
        }
        return memo[i][r] = Math.max(dfs(i + 1, r, nums, memo),
                dfs(i + 1, r + nums[i], nums, memo));
    }
}
