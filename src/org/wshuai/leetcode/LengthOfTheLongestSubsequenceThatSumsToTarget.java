package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 08/04/2025.
 * #2915 https://leetcode.com/problems/length-of-the-longest-subsequence-that-sums-to-target/
 */
public class LengthOfTheLongestSubsequenceThatSumsToTarget {

    // time O(n * target), space O(target)
    public int lengthOfLongestSubsequenceSpaceOptimized(List<Integer> nums, int target) {
        int n = nums.size();
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int[] next = new int[target + 1];
            for (int j = 1; j <= target; j++) {
                int num = nums.get(i - 1);
                next[j] = dp[j];
                if (j >= num && dp[j - num] != -1) {
                    next[j] = Math.max(next[j], dp[j - num] + 1);
                }
            }
            dp = next;
        }
        return dp[target];
    }

    // time O(n * target), space O(n * target)
    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int n = nums.size();
        int[][] dp = new int[n + 1][target + 1]; // dp[i][j] denotes longest subsequence end at i - 1 with sum j
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
            dp[i][0] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                int num = nums.get(i - 1);
                dp[i][j] = dp[i - 1][j]; // don't use nums[i - 1] to get sum j
                if (j >= num && dp[i - 1][j - num] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - num] + 1); // use nums[i - 1] to get sum j
                }
            }
        }
        return dp[n][target];
    }
}
