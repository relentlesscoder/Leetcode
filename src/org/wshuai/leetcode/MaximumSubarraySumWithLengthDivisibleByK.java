package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/28/2025.
 * #3381 https://leetcode.com/problems/maximum-subarray-sum-with-length-divisible-by-k/
 */
public class MaximumSubarraySumWithLengthDivisibleByK {

    // time O(n), space O(k)
    public long maxSubarraySum(int[] nums, int k) {
        // 利用性质两数对 k 取模结果相同则它们的差可以整除 k 。维护一个长度
        // 为 k 的数组 prefix 的每个值 prefix[i] 存数组索引对k取模为 i
        // 时的最小值。遍历数组，当当前索引与 k 取模等于 i 时减去 prefix[i]
        // 得到以当前索引结尾的长度可以整除 k 的子数组和的最大值。答案即为所有
        // 这些和的最大值。
        long res = Long.MIN_VALUE, sum = 0;
        int n = nums.length;
        long[] prefix = new long[k];
        Arrays.fill(prefix, Long.MAX_VALUE);
        prefix[0] = 0; // i 从 0 开始
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            int idx = (i + 1) % k;
            if (prefix[idx] != Long.MAX_VALUE) {
                res = Math.max(res, sum - prefix[idx]);
            }
            prefix[idx] = Math.min(sum, prefix[idx]);
        }
        return res;
    }

    // time O(n), space O(n)
    public long maxSubarraySumDP(int[] nums, int k) {
        // 将数组看作以 k 为长度的块，然后用 #0053 同样的方法求最大子数组和。
        long res = Long.MIN_VALUE;
        int n = nums.length;
        long[] prefixSum = new long[n + 1],
                dp = new long[n + 1]; // 以 n - 1 为结尾的块的最大子数组和
        // 计算前缀和
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        // 且以 k 为块组成的最大子数组
        for (int i = k; i <= n; i++) {
            // #0053
            // dp[i - k] 是 以 i - k - 1 为结尾的块的最大子数组和
            // prefixSum[i] - prefixSum[i - k] 是 [i - k, i - 1] 的块
            dp[i] = Math.max(dp[i - k], 0L) + prefixSum[i] - prefixSum[i - k];
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
