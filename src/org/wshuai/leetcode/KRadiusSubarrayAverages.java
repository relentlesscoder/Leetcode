package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/12/2023.
 * #2090 https://leetcode.com/problems/k-radius-subarray-averages/
 */
public class KRadiusSubarrayAverages {

    // time O(n), space O(n)
    public int[] getAveragesSlidingWindow(int[] nums, int k) {
        int n = nums.length, l = 2 * k + 1;
        long sum = 0;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (i > 2 * k) {
                sum -= nums[i - l];
            }
            if (i >= 2 * k) {
                res[i - k] = (int)(sum / l);
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public int[] getAveragesPrefixSum(int[] nums, int k) {
        int n = nums.length, l = 2 * k + 1;
        long[] prefixSum = new long[n + 1];
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        for (int i = l - 1; i < n; i++) {
            long sum = prefixSum[i + 1] - prefixSum[i - l + 1];
            res[i - k] = (int) (sum / l);
        }
        return res;
    }
}
