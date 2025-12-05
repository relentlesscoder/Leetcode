package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/07/2025.
 * #3040 https://leetcode.com/problems/maximum-number-of-operations-with-the-same-score-ii/
 */
public class MaximumNumberOfOperationsWithTheSameScoreII {

    // time O(n^2), space O(n^2)
    public int maxOperationsDP(int[] nums) {
        int n = nums.length;
        int res1 = findMaxOperations(nums, 1, n - 2, nums[0] + nums[n - 1]);
        int res2 = findMaxOperations(nums, 2, n - 1, nums[0] + nums[1]);
        int res3 = findMaxOperations(nums, 0, n - 3, nums[n - 1] + nums[n - 2]);
        return Math.max(res1, Math.max(res2, res3)) + 1;
    }

    private int findMaxOperations(int[] nums, int start, int end, int score) {
        // https://leetcode.cn/problems/maximum-number-of-operations-with-the-same-score-ii/solutions/2643756/qu-jian-dp-de-tao-lu-pythonjavacgo-by-en-nynz/
        int n = nums.length;
        // dp[i][j + 1] is the max operations in subarray [i, j]
        // we use j + 1 simply to avoid -1 by j - 1
        int[][] dp = new int[n + 1][n + 1];
        // i = end - 1 because i needs to left to j
        for (int i = end; i >= start; i--) {
            for (int j = i + 1; j <= end; j++) {
                if (nums[i] + nums[j] == score) {
                    dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j] + 1);
                }
                if (nums[i] + nums[i + 1] == score) {
                    dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i + 2][j + 1] + 1);
                }
                if (nums[j] + nums[j - 1] == score) {
                    dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i][j - 1] + 1);
                }
            }
        }
        return dp[start][end + 1];
    }

    // time O(n^2), space O(n^2)
    public int maxOperationsMemorization(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        int res1 = dfs(nums, 0, n - 1, nums[0] + nums[n - 1], memo);
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        int res2 = dfs(nums, 0, n - 1, nums[0] + nums[1], memo);
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        int res3 = dfs(nums, 0, n - 1, nums[n - 1] + nums[n - 2], memo);
        return Math.max(res1, Math.max(res2, res3));
    }

    private int dfs(int[] nums, int i, int j, int score, int[][] memo) {
        if (i >= j) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = 0;
        if (nums[i] + nums[j] == score) {
            res = Math.max(res, 1 + dfs(nums, i + 1, j - 1, score, memo));
        }
        if (nums[i] + nums[i + 1] == score) {
            res = Math.max(res, 1 + dfs(nums, i + 2, j, score, memo));
        }
        if (nums[j] + nums[j - 1] == score) {
            res = Math.max(res, 1 + dfs(nums, i, j - 2, score, memo));
        }
        return memo[i][j] = res;
    }
}
