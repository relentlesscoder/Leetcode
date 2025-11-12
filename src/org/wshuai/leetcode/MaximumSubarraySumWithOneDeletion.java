package org.wshuai.leetcode;

/**
 * Created by Wei on 11/09/2019.
 * #1186 https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/
 */
public class MaximumSubarraySumWithOneDeletion {

    // time O(n), space O(n)
    public int maximumSum(int[] arr) {
        int res = Integer.MIN_VALUE;
        int prefixSum = Integer.MIN_VALUE / 2;
        int n = arr.length;
        int[] pre = new int[n];
        // Calculate prefix sum ends with index i if nums[i] is deleted,
        // Note this starts with 1 since empty prefix is not valid.
        for (int i = 1; i < n; i++) {
            prefixSum = Math.max(prefixSum, 0) + arr[i - 1];
            res = Math.max(res, prefixSum);
            pre[i] = prefixSum;
        }
        // If we add every numbers then this equals calculating max subarray sum
        // without deleting any numbers.
        res = Math.max(res, Math.max(prefixSum, 0) + arr[n - 1]);
        // Calculate suffix sum ends with index i if nums[i] is deleted,
        // Note this starts with n - 2 since empty suffix is not valid.
        int postfixSum = Integer.MIN_VALUE / 2;
        for (int i = n - 2; i >= 0; i--) {
            postfixSum = Math.max(postfixSum, 0) + arr[i + 1];
            // maxSubarraySum_d (with deletion) = max(pre[i] + suf[i], pre[i], suf[i])
            // maxSubarraySum (without deletion) = maxSum
            // res = Math.max(maxSubarraySum_d, maxSubarraySum)
            res = Math.max(res, Math.max(postfixSum, pre[i] + postfixSum));
        }
        return res;
    }
}
