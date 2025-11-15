package org.wshuai.leetcode;

/**
 * Created by Wei on 11/15/2025.
 * #3584 https://leetcode.com/problems/maximum-product-of-first-and-last-elements-of-a-subsequence/
 */
public class MaximumProductOfFirstAndLastElementsOfASubsequence {

    // time O(n), space O(n)
    public long maximumProduct(int[] nums, int m) {
        long res = Long.MIN_VALUE;
        int n = nums.length, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // For index i, the largest index can be the head of the subsequence
            // is i + 1 - m
            if (i + 1 - m >= 0) {
                max = Math.max(max, nums[i + 1 - m]);
                min = Math.min(min, nums[i + 1 - m]);
            }
            // i needs to be at least m - 1 to form a m length subsequence
            if (i >= m - 1) {
                // nums[i] is positive
                res = Math.max(res, 1L * nums[i] * max);
                // nums[i] is negative
                res = Math.max(res, 1L * nums[i] * min);
            }
        }
        return res;
    }
}
