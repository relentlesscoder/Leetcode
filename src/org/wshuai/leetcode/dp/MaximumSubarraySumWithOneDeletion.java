package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 11/09/2019.
 * #1186 https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/
 */
public class MaximumSubarraySumWithOneDeletion {

    // time O(n), space O(1)
    public int maximumSumDP(int[] arr) {
        // 空间优化版 DP
        int n = arr.length;
        int s0 = Integer.MIN_VALUE / 2, s1 = Integer.MIN_VALUE / 2;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int c0 = Math.max(s0, 0) + arr[i];
            int c1 = Math.max(s1 + arr[i], s0);
            res = Math.max(res, Math.max(c0, c1));
            s0 = c0;
            s1 = c1;
        }
        return res;
    }

    // time O(n), space O(n)
    public int maximumSumDPWithArray(int[] arr) {
        int n = arr.length;
        // dp[i][0] 表示以 i - 1 结尾不删除元素的最大子数组值
        // dp[i][1] 表示以 i - 1 结尾删除一个元素的最大子数组值
        int[][] dp = new int[n + 1][2];
        Arrays.fill(dp[0], Integer.MIN_VALUE / 2);
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            dp[i + 1][0] = Math.max(dp[i][0], 0) + arr[i]; // 不删除元素的情况即为 #0053
            // 删除元素的情况分为两种:
            //   1. 删除 i -> dp[i][0]
            //   2. 不删除 i -> dp[i][1] + arr[i]
            dp[i + 1][1] = Math.max(dp[i][1] + arr[i], dp[i][0]);
            res = Math.max(res, Math.max(dp[i + 1][0], dp[i + 1][1]));
        }
        return res;
    }

    // time O(n), space O(n)
    public int maximumSumDFSWithMemorization(int[] arr) {
        int n = arr.length;
        int[][] memo = new int[n][2];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, Math.max(dfs(i, 0, arr, memo), dfs(i, 1, arr, memo)));
        }
        return res;
    }

    private int dfs(int i, int j, int[] arr, int[][] memo) {
        if (i < 0) {
            return Integer.MIN_VALUE / 2;
        }
        if (memo[i][j] != Integer.MIN_VALUE) {
            return memo[i][j];
        }
        if (j == 0) {
            return memo[i][j] = Math.max(dfs(i - 1, 0, arr, memo), 0) + arr[i];
        }
        return memo[i][j] = Math.max(dfs(i - 1, 1, arr, memo) + arr[i],
                dfs(i - 1, 0, arr, memo));
    }

    // time O(n), space O(n)
    public int maximumSumPrefixSum(int[] arr) {
        int res = Integer.MIN_VALUE,
                prefixSum = Integer.MIN_VALUE / 2,
                postfixSum = Integer.MIN_VALUE / 2,
                n = arr.length;
        int[] pre = new int[n];
        // 计算删除 arr[i] 的最大前缀和
        for (int i = 1; i < n; i++) { // 前缀不为空所以从 1 开始 - 前缀或者后缀为空会被 pre[i] 处理
            // #0053 计算最大前缀和
            prefixSum = Math.max(prefixSum, 0) + arr[i - 1];
            res = Math.max(res, prefixSum); // 顺手统计不删除元素的最大子数组和
            pre[i] = prefixSum; // 记录前缀和
        }
        // 记得统计最后一个元素的前缀和
        res = Math.max(res, Math.max(prefixSum, 0) + arr[n - 1]);
        // 用同样的方法计算删除 arr[i] 最大后缀和
        for (int i = n - 2; i >= 0; i--) { // 后缀不为空所以从 n - 2 开始
            postfixSum = Math.max(postfixSum, 0) + arr[i + 1];
            // 两种情况:
            //   1. 不删除 -> 最大前缀和 pre[i]
            //   2. 删除 -> pre[i] + suf[i]
            res = Math.max(res, pre[i] + postfixSum);
        }
        return res;
    }
}
