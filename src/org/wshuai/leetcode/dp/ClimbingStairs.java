package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 10/26/2016.
 * #0070 https://leetcode.com/problems/climbing-stairs/
 */
public class ClimbingStairs {

    // time O(n), space O(1)
    public int climbStairsDP(int n) {
        // 空间优化版，用两个变量存 i - 1 和 i - 2 的结果
        int res = 1, pre = 1;
        for (int i = 2; i <= n; i++) {
            int cnt = res + pre;
            pre = res;
            res = cnt;
        }
        return res;
    }

    // time O(n), space O(n)
    public int climbStairsDPWithArray(int n) {
        int[] dp = new int[n + 1]; // dp[i] 表示爬到第 i 级楼梯有多少种方法
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; // 可以从 i - 1 或者 i - 2 级爬到第 i 级
        }
        return dp[n];
    }
}
