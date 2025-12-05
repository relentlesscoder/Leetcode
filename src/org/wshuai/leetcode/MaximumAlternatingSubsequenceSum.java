package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2025.
 * #1911 https://leetcode.com/problems/maximum-alternating-subsequence-sum/
 */
public class MaximumAlternatingSubsequenceSum {

    // time O(n), space O(n)
    public long maxAlternatingSum(int[] nums) {
        /**
         * dp[i][0] denotes max sum of even sequence in first i numbers
         * dp[i][1] denotes max sum of odd sequence in first i numbers
         * dp[i+1][0] = max(dp[i][0], dp[i][1] − nums[i])
         * dp[i+1][1] = max(dp[i][1], dp[i][0] + nums[i])
         */
        int n = nums.length;
        long[][] dp = new long[n + 1][2];
        for (int i = 0; i < n; i++) {
            dp[i + 1][0] = Math.max(dp[i][0], dp[i][1] - nums[i]);
            dp[i + 1][1] = Math.max(dp[i][1], dp[i][0] + nums[i]);
        }
        return Math.max(dp[n][0], dp[n][1]);
    }

    // time O(n), space O(1)
    public long maxAlternatingSumSpaceOptimized(int[] nums) {
        int n = nums.length;
        long even = 0, odd = 0;
        for (int i = 0; i < n; i++) {
            long nextEven = Math.max(even, odd - nums[i]);
            long nextOdd = Math.max(odd, even + nums[i]);
            even = nextEven;
            odd = nextOdd;
        }
        return Math.max(even, odd);
    }
}
