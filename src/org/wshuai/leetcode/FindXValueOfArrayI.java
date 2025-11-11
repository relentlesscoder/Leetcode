package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/2025.
 * #3524 https://leetcode.com/problems/find-x-value-of-array-i/
 */
public class FindXValueOfArrayI {

    // time O(n * k), space O(k)
    public long[] resultArraySpaceOptimized(int[] nums, int k) {
        int n = nums.length;
        long[] res = new long[k];
        long[] prev = new long[k];
        for (int i = 0; i < n; i++) {
            long[] next = new long[k];
            int v = nums[i] % k;
            next[v] = 1;
            for (int y = 0; y < k; y++) {
                next[y * v % k] += prev[y];
            }
            for (int z = 0; z < k; z++) {
                res[z] += next[z];
            }
            prev = next;
        }
        return res;
    }

    // time O(n * k), space O(n * k)
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] res = new long[k];
        long[][] dp = new long[n + 1][k];
        for (int i = 0; i < n; i++) {
            int v = nums[i] % k;
            dp[i + 1][v] = 1;
            for (int y = 0; y < k; y++) {
                dp[i + 1][y * v % k] += dp[i][y];
            }
            for (int z = 0; z < k; z++) {
                res[z] += dp[i + 1][z];
            }
        }
        return res;
    }
}
