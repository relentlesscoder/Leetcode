package org.wshuai.leetcode;

/**
 * Created by Wei on 08/16/2025.
 * #3201 https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-i/
 */
public class FindTheMaximumLengthOfValidSubsequenceI {

    // time O(n * 2), space O(1)
    public int maximumLengthDP(int[] nums) {
        // same as #3202 https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-ii/
        int[][] dp = new int[2][2];
        int res = 0;
        for (int num : nums) {
            num %= 2;
            for (int prev = 0; prev < 2; prev++) {
                dp[prev][num] = dp[num][prev] + 1;
                res = Math.max(res, dp[prev][num]);
            }
        }
        return res;
    }

    // time O(n), space O(1)
    public int maximumLength(int[] nums) {
        int res = 0;
        // dp[0] = 00, dp[1] = 01, dp[2] = 10, dp[3] = 11
        int[] dp = new int[4];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                res = Math.max(res, ++dp[0]);
                if (dp[1] % 2 == 0) {
                    res = Math.max(res, ++dp[1]);
                }
                if (dp[2] % 2 == 1) {
                    res = Math.max(res, ++dp[2]);
                }
            } else {
                res = Math.max(res, ++dp[3]);
                if (dp[1] % 2 == 1) {
                    res = Math.max(res, ++dp[1]);
                }
                if (dp[2] % 2 == 0) {
                    res = Math.max(res, ++dp[2]);
                }
            }
        }
        return res;
    }
}
