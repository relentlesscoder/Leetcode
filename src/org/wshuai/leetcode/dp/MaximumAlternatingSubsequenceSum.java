package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 09/20/2025.
 * #1911 https://leetcode.com/problems/maximum-alternating-subsequence-sum/
 */
public class MaximumAlternatingSubsequenceSum {

    // time O(n), space O(1)
    public long maxAlternatingSumDP(int[] nums) {
        // 核心思路: 状态机DP, 交替加减 = 奇数位加偶数位减, 等价于买卖股票(无限次)
        // d0: 下一个选的元素做加法(奇数位)的最大和, d1: 下一个做减法(偶数位)的最大和
        int n = nums.length;
        long d0 = Long.MIN_VALUE / 2, d1 = 0L; // d0初始不合法, d1初始为0(还没选过)
        for (int i = 0; i < n; i++) {
            long temp = d0;
            // 加法位: 不选(保持d0) 或 从减法位转移过来加上nums[i]
            d0 = Math.max(d0, d1 + nums[i]);
            // 减法位: 不选(保持d1) 或 从加法位转移过来减去nums[i]
            d1 = Math.max(d1, temp - nums[i]);
        }
        return Math.max(d0, d1);
    }

    // time O(n), space O(n)
    public long maxAlternatingSumDPWithGrid(int[] nums) {
        // 核心思路: 与滚动变量版相同, 但保留完整二维数组
        // dp[i][0]: 前i个元素, 下一个做加法的最大和, dp[i][1]: 下一个做减法的最大和
        int n = nums.length;
        long[][] dp = new long[n + 1][2];
        dp[0][0] = Long.MIN_VALUE / 2; // 初始没选过, 加法位不合法
        for (int i = 0; i < n; i++) {
            // 加法位: 不选 或 选nums[i]做加法(从减法位转移)
            dp[i + 1][0] = Math.max(dp[i][0], dp[i][1] + nums[i]);
            // 减法位: 不选 或 选nums[i]做减法(从加法位转移)
            dp[i + 1][1] = Math.max(dp[i][1], dp[i][0] - nums[i]);
        }
        return Math.max(dp[n][0], dp[n][1]);
    }

    // time O(n), space O(n)
    public long maxAlternatingSumDFSWithMemorization(int[] nums) {
        // 核心思路: 记忆化DFS, 从右往左决策每个元素选或不选, 选则交替加减
        int n = nums.length;
        // memo[i][j]: 前i+1个元素, 状态j(0=加法位, 1=减法位)的最大和
        long[][] memo = new long[n][2];
        for (long[] row : memo) {
            Arrays.fill(row, -1L);
        }
        return Math.max(dfs(n - 1, 0, nums, memo), dfs(n - 1, 1, nums, memo));
    }

    // i: 当前位置, j: 0=下一个做加法, 1=下一个做减法
    private long dfs(int i, int j, int[] nums, long[][] memo) {
        if (i == -1) {
            // 减法位初始为0(还没选过), 加法位不合法(必须先有加法才能减)
            return j == 1 ? 0L : Long.MIN_VALUE / 2;
        }
        if (memo[i][j] != -1L) {
            return memo[i][j];
        }
        if (j == 0) {
            // 加法位: 不选 或 选nums[i]做加法(从减法位转移)
            return memo[i][j] = Math.max(dfs(i - 1, 0, nums, memo), dfs(i - 1, 1, nums, memo) + nums[i]);
        }
        // 减法位: 不选 或 选nums[i]做减法(从加法位转移)
        return memo[i][j] = Math.max(dfs(i - 1, 1, nums, memo), dfs(i - 1, 0, nums, memo) - nums[i]);
    }
}
