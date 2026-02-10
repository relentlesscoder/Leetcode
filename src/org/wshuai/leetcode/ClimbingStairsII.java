package org.wshuai.leetcode;

/**
 * Created by Wei on 02/09/2026.
 * #3693 https://leetcode.cn/problems/climbing-stairs-ii/
 */
public class ClimbingStairsII {

    // time O(n), space O(1)
    public int climbStairsDP(int n, int[] costs) {
        // 空间优化版
        int s1 = 0, s2 = 0, s3 = 0;
        for (int i = 1; i <= n; i++) {
            int c = s1 + costs[i - 1] + 1;
            if (i >= 2) {
                c = Math.min(c, s2 + costs[i - 1] + 4);
            }
            if (i >= 3) {
                c = Math.min(c, s3 + costs[i - 1] + 9);
            }
            s3 = s2;
            s2 = s1;
            s1 = c;
        }
        return s1;
    }

    // time O(n), space O(n)
    public int climbStairsDPWithArray(int n, int[] costs) {
        // dp[i] 是到第 i 级台阶的最小总成本，起始在第 0 级台阶花费为 0 。
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) { // 从第一级台阶开始算
            // 注意 cost 是从 1 开始的
            int c = dp[i - 1] + costs[i - 1] + 1; // 计算跳一步到 i 的花费
            if (i >= 2) { // 计算跳两步到 i 的花费
                c = Math.min(c, dp[i - 2] + costs[i - 1] + 4);
            }
            if (i >= 3) { // 计算跳三步到 i 的花费
                c = Math.min(c, dp[i - 3] + costs[i - 1] + 9);
            }
            dp[i] = c;
        }
        return dp[n];
    }
}
