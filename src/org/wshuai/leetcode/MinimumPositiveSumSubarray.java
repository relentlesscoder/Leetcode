package org.wshuai.leetcode;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by Wei on 12/31/2025.
 * #3364 https://leetcode.com/problems/minimum-positive-sum-subarray/
 */
public class MinimumPositiveSumSubarray {

    // time O(n * log(n)), space O(n)
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        // 子数组和大于 0 ，用前缀和差来表示子数组的和则:
        //  s[i] - s[j] > 0 -> s[i] > s[j]
        // 题目要求子数组的和最小，则我们要找到满足上述条件的最大 s[j]。
        // 满足要求的子数组还必须满足条件:
        //  l <= i - j <= r
        // 所以对每个前缀和 s[i] 来说，可以考虑的前缀和 s[j] 必须满足:
        //  j 的取值范围在 [max(i - r, 0), max(i - l, 0)] 之内。
        // 我们可以用一个有序哈希表来存前缀和及其出现的次数，遍历数组在可考虑的范围内
        // 在有序哈希表中找到小于当前前缀和的最大值 - 它们的差即为当前位置和大于 0
        // 的子数组的最小值。
        int n = nums.size(), res = Integer.MAX_VALUE;
        int[] prefix = new int[n + 1];
        // 预先计算前缀和数组
        for (int i = 0; i < n; i++) { // O(n)
            prefix[i + 1] = prefix[i] + nums.get(i);
        }
        // 有序哈希表
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 1; i <= n; i++) { // O(n)
            // 加入一个 i - l 前缀和
            if (i - l >= 0) {
                map.merge(prefix[i - l], 1, Integer::sum); // O(log(n))
            }
            // 找到小于当前前缀和的最大值
            Integer lower = map.lowerKey(prefix[i]); // O(log(n))
            if (lower != null) {
                res = Math.min(res, prefix[i] - lower);
            }
            // 移除一个 i - r 前缀和
            if (i - r >= 0) {
                int cnt = map.merge(prefix[i - r], -1, Integer::sum); // O(log(n))
                if (cnt == 0) {
                    map.remove(prefix[i - r]); // O(log(n))
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // time O(n * r), space O(n)
    public int minimumSumSubarrayBF(List<Integer> nums, int l, int r) {
        // 由于数组长度不大，直接暴力找出每个可能长度对应的和大于 0 的子数组的最小值
        int n = nums.size(), res = Integer.MAX_VALUE;
        int[] prefix = new int[n + 1];
        // 预先计算前缀和数组
        for (int i = 0; i < n; i++) { // O(n)
            prefix[i + 1] = prefix[i] + nums.get(i);
        }
        // 遍历所有可能的长度
        for (; l <= r; l++) { // O(r)
            // 用定长滑窗计算长度为 l 的子数组的和
            for (int i = l - 1; i < n; i++) { // O(n)
                // 用前缀和数组在 O(1) 时间内计算子数组的和
                int sum = prefix[i + 1] - prefix[i + 1 - l]; // O(1)
                if (sum > 0) {
                    res = Math.min(res, sum);
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
