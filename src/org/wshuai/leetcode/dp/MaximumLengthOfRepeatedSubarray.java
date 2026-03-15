package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 10/04/2019.
 * #0718 https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 */
public class MaximumLengthOfRepeatedSubarray {

    // time O(m * n), space O(n)
    public int findLength(int[] nums1, int[] nums2) {
        // 空间优化版 DP
        int res = 0, m = nums1.length, n = nums2.length;
        int[] dp = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int pre = dp[0];
            for (int j = 0; j < n; j++) {
                int x = dp[j + 1];
                if (nums1[i] == nums2[j]) {
                    dp[j + 1] = 1 + pre;
                    res = Math.max(res, dp[j + 1]);
                } else {
                    dp[j + 1] = 0;
                }
                pre = x;
            }
        }
        return res;
    }

    // time O(m * n), space O(m * n)
    public int findLengthDPWithGrid(int[] nums1, int[] nums2) {
        int res = 0, m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i + 1][j + 1] = 1 + dp[i][j];
                    res = Math.max(res, dp[i + 1][j + 1]);
                }
            }
        }
        return res;
    }
}
