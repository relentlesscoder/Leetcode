package org.wshuai.leetcode;

/**
 * Created by Wei on 09/27/2019.
 * #0974 https://leetcode.com/problems/subarray-sums-divisible-by-k/
 */
public class SubarraySumsDivisibleByK {

    // time O(n), space O(k)
    public int subarraysDivByK(int[] nums, int k) {
        // 性质：如果两数与k取模的结果相同，则它们的差可以整除k
        int res = 0, n = nums.length;
        // 前缀和数组count[i]存与k取模结果为i的前缀数组和的数量，
        // 数字与k取模的结果在区间[0, k - 1]内所以数组长度为k。
        int[] count = new int[k];
        count[0] = 1;
        for (int i = 0, sum = 0; i < n; i++) {
            sum += nums[i];
            int mod = (sum % k + k) % k; // 注意前缀和可能为负数
            res += count[mod]; // 更新答案
            count[mod]++; // 维护前缀和数量
        }
        return res;
    }
}
