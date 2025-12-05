package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/30/2025.
 * #3634 https://leetcode.com/problems/minimum-removals-to-balance-array/
 */
public class MinimumRemovalsToBalanceArray {

    // time O(n * log(n)), space O(log(n))
    public int minRemoval(int[] nums, int k) {
        int res = 0, n = nums.length;
        Arrays.sort(nums);
        for (int i = 0, j = 0; i < n; i++) {
            while ((long) nums[i] > (long) nums[j] * k) {
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return n - res;
    }
}
