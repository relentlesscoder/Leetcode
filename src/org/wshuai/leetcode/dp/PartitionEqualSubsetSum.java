package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 11/11/2016.
 * #0416 https://leetcode.com/problems/partition-equal-subset-sum/
 */
public class PartitionEqualSubsetSum {

    // time O(n * t), space O(t)
    public boolean canPartitionDP(int[] nums) {
        // 空间优化版 DP
        int n = nums.length, t = 0;
        for (int x : nums) {
            t += x;
        }
        if (t % 2 == 1) {
            return false;
        }
        t /= 2;
        boolean[] pre = new boolean[t + 1];
        pre[0] = true;
        for (int i = 0; i < n; i++) {
            boolean[] dp = new boolean[t + 1];
            for (int j = 0; j <= t; j++) {
                if (j < nums[i]) {
                    dp[j] = pre[j];
                } else {
                    dp[j] = pre[j] || pre[j - nums[i]];
                }
            }
            pre = dp;
        }
        return pre[t];
    }

    // time O(n * t), space O(n * t)
    public boolean canPartitionDPWithGrid(int[] nums) {
        // 把记忆化搜索翻译成 DP
        int n = nums.length, t = 0;
        for (int x : nums) {
            t += x;
        }
        if (t % 2 == 1) {
            return false;
        }
        t /= 2;
        boolean[][] dp = new boolean[n + 1][t + 1];
        dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= t; j++) {
                if (j < nums[i]) {
                    dp[i + 1][j] = dp[i][j];
                } else {
                    dp[i + 1][j] = dp[i][j] || dp[i][j - nums[i]];
                }
            }
        }
        return dp[n][t];
    }

    // time O(n * t), space O(n * t)
    public boolean canPartitionDFSWithMemorization(int[] nums) {
        // 记忆化搜索 - 0/1 背包
        int n = nums.length, t = 0;
        for (int x : nums) {
            t += x;
        }
        if (t % 2 == 1) {
            return false;
        }
        t /= 2;
        Boolean[][] memo = new Boolean[n][t + 1];
        return dfs(n - 1, t, nums, memo);
    }

    private boolean dfs(int i, int t, int[] nums, Boolean[][] memo) {
        if (i == -1) {
            return t == 0;
        }
        if (memo[i][t] != null) {
            return memo[i][t];
        }
        if (t < nums[i]) {
            return memo[i][t] = dfs(i - 1, t, nums, memo);
        } else {
            return memo[i][t] = dfs(i - 1, t, nums, memo)
                    || dfs(i - 1, t - nums[i], nums, memo);
        }
    }
}
