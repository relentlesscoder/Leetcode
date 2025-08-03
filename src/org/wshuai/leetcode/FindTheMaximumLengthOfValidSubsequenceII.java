package org.wshuai.leetcode;

/**
 * Created by Wei on 08/03/2025.
 * #3202 https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-ii/
 */
public class FindTheMaximumLengthOfValidSubsequenceII {

    // time O(n * k), space O(k^2)
    public int maximumLength(int[] nums, int k) {
        // valid subsequence like [1,2,3,4,5,6] follows pattern
        // all values at even index be same modulo k [2,4,6]
        // and all values at odd index be same modulo k [1,3,5]
        int[][] dp = new int[k][k];
        int res = 0;
        for (int num : nums) {
            num %= k;
            for (int prev = 0; prev < k; prev++) {
                dp[prev][num] = dp[num][prev] + 1;
                res = Math.max(res, dp[prev][num]);
            }
        }
        return res;
    }
}
