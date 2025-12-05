package org.wshuai.leetcode;

/**
 * Created by Wei on 09/26/2025.
 * #3065 https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-i/
 */
public class MinimumOperationsToExceedThresholdValueI {

    // time O(n), space O(1)
    public int minOperations(int[] nums, int k) {
        int res = 0;
        for (int num : nums) {
            if (num < k) {
                res++;
            }
        }
        return res;
    }
}
