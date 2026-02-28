package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/25/2017.
 * #0494 https://leetcode.com/problems/target-sum/
 */
public class TargetSum {

    // time O(n * t), space O(t)
    public int findTargetSumWays(int[] nums, int target) {
        // 空间优化版 DP
        int n = nums.length, sum = 0;
        for (int x : nums) {
            sum += x;
        }
        int t = target + sum;
        if (t < 0 || t % 2 == 1) {
            return 0;
        }
        t /= 2;
        int[] pre = new int[t + 1];
        pre[0] = 1;
        for (int i = 0; i < n; i++) {
            int[] dp = new int[t + 1];
            for (int j = 0; j <= t; j++) {
                dp[j] = j < nums[i] ? pre[j] : pre[j - nums[i]] + pre[j];
            }
            pre = dp;
        }
        return pre[t];
    }

    // time O(n * t), space O(n * t)
    public int findTargetSumWaysDPWithGrid(int[] nums, int target) {
        // 把记忆化搜索翻译成 DP
        int n = nums.length, sum = 0;
        for (int x : nums) {
            sum += x;
        }
        int t = target + sum;
        if (t < 0 || t % 2 == 1) {
            return 0;
        }
        t /= 2;
        int[][] dp = new int[n + 1][t + 1];
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= t; j++) {
                dp[i + 1][j] = j < nums[i] ? dp[i][j] : dp[i][j - nums[i]] + dp[i][j];
            }
        }
        return dp[n][t];
    }

    // time O(n * t), space O(n * t)
    public int findTargetSumWaysDFSWithMemorization(int[] nums, int target) {
        // 记忆化搜索
        // 设数组和为 s， 目标和为 t，加加号的数字和为 p，则加减号的数字和为 s - p 。
        //   p - (s - p) = t
        //   2p = s + t
        //   p = (s + t) / 2;
        // 题目转化成从数组中选出一些数使的它们的和为 p ，此为背包问题。
        int n = nums.length, sum = 0;
        for (int x : nums) {
            sum += x;
        }
        target += sum;
        // 由推导出的公式可知 s + t 非负且能为偶数
        if (target < 0 || target % 2 == 1) {
            return 0;
        }
        target /= 2;
        int[][] memo = new int[n][target + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(n - 1, target, nums, memo);
    }

    private int dfs(int i, int t, int[] nums, int[][] memo) {
        if (i == -1) {
            return t == 0 ? 1 : 0;
        }
        if (memo[i][t] != -1) {
            return memo[i][t];
        }
        // 只能不选当前数
        if (t < nums[i]) {
            return memo[i][t] = dfs(i - 1, t, nums, memo);
        }
        // 选当前数或者不选当前数
        return memo[i][t] = dfs(i - 1, t - nums[i], nums, memo)
                + dfs(i - 1, t, nums, memo);
    }
}
