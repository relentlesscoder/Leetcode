package org.wshuai.leetcode.dp;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 08/04/2025.
 * #2915 https://leetcode.com/problems/length-of-the-longest-subsequence-that-sums-to-target/
 */
public class LengthOfTheLongestSubsequenceThatSumsToTarget {

    private static final int MIN = -(int) 1e4;

    // time O(n * t), space O(t)
    public int lengthOfLongestSubsequenceDP(List<Integer> nums, int target) {
        // 空间优化版 DP
        int n = nums.size();
        int[] pre = new int[target + 1];
        Arrays.fill(pre, MIN);
        pre[0] = 0;
        for (int i = 0; i < n; i++) {
            int[] dp = new int[target + 1];
            for (int j = 0; j <= target; j++) {
                if (j < nums.get(i)) {
                    dp[j] = pre[j];
                } else {
                    dp[j] = Math.max(pre[j], 1 + pre[j - nums.get(i)]);
                }
            }
            pre = dp;
        }
        return pre[target] < 0 ? -1 : pre[target];
    }

    // time O(n * t), space O(n * t)
    public int lengthOfLongestSubsequenceDPWithGrid(List<Integer> nums, int target) {
        // 把记忆化搜索翻译成 DP
        int n = nums.size();
        int[][] dp = new int[n + 1][target + 1];
        Arrays.fill(dp[0], MIN);
        dp[0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                if (j < nums.get(i)) {
                    dp[i + 1][j] = dp[i][j];
                } else {
                    dp[i + 1][j] = Math.max(dp[i][j], 1 + dp[i][j - nums.get(i)]);
                }
            }
        }
        return dp[n][target] < 0 ? -1 : dp[n][target];
    }

    // time O(n * t), space O(n * t)
    public int lengthOfLongestSubsequenceDFSWithMemorization(List<Integer> nums, int target) {
        // 记忆化搜索 - 0/1 背包
        int n = nums.size();
        int[][] memo = new int[n][target + 1];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        int res = dfs(n - 1, target, nums, memo);
        return res < 0 ? -1 : res;
    }

    private int dfs(int i, int t, List<Integer> nums, int[][] memo) {
        if (i == -1) {
            return t == 0 ? 0 : MIN;
        }
        if (memo[i][t] != Integer.MIN_VALUE) {
            return memo[i][t];
        }
        if (t < nums.get(i)) {
            return memo[i][t] = dfs(i - 1, t, nums, memo);
        } else {
            return memo[i][t] = Math.max(dfs(i - 1, t, nums, memo),
                    1 + dfs(i - 1, t - nums.get(i), nums, memo));
        }
    }
}
