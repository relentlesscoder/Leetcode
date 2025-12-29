package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/28/2025.
 * #3381 https://leetcode.com/problems/maximum-subarray-sum-with-length-divisible-by-k/
 */
public class MaximumSubarraySumWithLengthDivisibleByK {

    // time O(n), space O(k)
    public long maxSubarraySum(int[] nums, int k) {
        // 利用性质两数对k取模结果相同则它们的差可以整除 k 。维护一个长度
        // 为 k 的数组 prefix 的每个值 prefix[i] 存数组索引对k取模为
        // i 时的最小值。遍历数组，当当前索引与 k 取模等于 i 时减去
        // prefix[i] 得到以当前索引结尾的长度可以整除 k 的子数组和的最大
        // 值。答案即为所有这些和的最大值。
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
}
