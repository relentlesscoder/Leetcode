package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 10/18/2019.
 * #1049 https://leetcode.com/problems/last-stone-weight-ii/
 */
public class LastStoneWeightII {

    // time O(n * t), space O(t)
    public int lastStoneWeightII(int[] stones) {
        // 空间优化版 DP
        int n = stones.length, sum = 0, target = 0;
        for (int s : stones) {
            sum += s;
        }
        target = sum / 2;
        int[] dp = new int[target + 1];
        Arrays.setAll(dp, i -> i);
        for (int i = 0; i < n; i++) {
            for (int t = target; t >= 0; t--) {
                dp[t] = Math.min(dp[t],
                        t < stones[i] ? 10000 : dp[t - stones[i]]);
            }
        }
        return sum - 2 * (target - dp[target]);
    }

    // time O(n * t), space O(n * t)
    public int lastStoneWeightIIDPWithGrid(int[] stones) {
        // 把记忆化搜索翻译成 DP
        int n = stones.length, sum = 0, target = 0;
        for (int s : stones) {
            sum += s;
        }
        target = sum / 2;
        int[][] dp = new int[n + 1][target + 1];
        Arrays.setAll(dp[0], i -> i);
        for (int i = 0; i < n; i++) {
            for (int t = target; t >= 0; t--) {
                dp[i + 1][t] = Math.min(dp[i][t],
                        t < stones[i] ? 10000 : dp[i][t - stones[i]]);
            }
        }
        return sum - 2 * (target - dp[n][target]);
    }

    // time O(n * t), space O(n * t)
    public int lastStoneWeightIIDFSWithMemorization(int[] stones) {
        // 记忆化搜索
        int n = stones.length, sum = 0, target = 0;
        for (int s : stones) {
            sum += s;
        }
        target = sum / 2;
        int[][] memo = new int[n][target + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return sum - 2 * (target - dfs(n - 1, target, stones, memo));
    }

    private int dfs(int i, int target, int[] stones, int[][] memo) {
        if (target < 0) {
            return 10000;
        }
        if (i == -1) {
            return target;
        }
        if (memo[i][target] != -1) {
            return memo[i][target];
        }
        return memo[i][target] = Math.min(dfs(i - 1, target - stones[i], stones, memo),
                dfs(i - 1, target, stones, memo));
    }
}
