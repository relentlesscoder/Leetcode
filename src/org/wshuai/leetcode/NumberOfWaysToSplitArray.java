package org.wshuai.leetcode;

/**
 * Created by Wei on 08/02/2025.
 * #2270 https://leetcode.com/problems/number-of-ways-to-split-array/
 */
public class NumberOfWaysToSplitArray {

    // time O(n), space O(1)
    public int waysToSplitArray(int[] nums) {
        int res = 0, n = nums.length;
        long prefixSum = 0, sum = 0;
        for (int num : nums) {
            sum += num;
        }
        for (int i = 0; i < n - 1; i++) {
            prefixSum += nums[i];
            if (prefixSum >= sum - prefixSum) {
                res++;
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public int waysToSplitArrayPrefixSumArray(int[] nums) {
        int res = 0, n = nums.length;
        long prefixSum = 0;
        long[] postfixSum = new long[n];
        for (int i = n - 2; i >= 0; i--) {
            postfixSum[i] += postfixSum[i + 1] + nums[i + 1];
        }
        for (int i = 0; i < n - 1; i++) {
            prefixSum += nums[i];
            if (prefixSum >= postfixSum[i]) {
                res++;
            }
        }
        return res;
    }
}
