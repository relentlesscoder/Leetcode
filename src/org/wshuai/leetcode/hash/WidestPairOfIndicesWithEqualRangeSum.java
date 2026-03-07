package org.wshuai.leetcode.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/30/2025.
 * #1983 https://leetcode.com/problems/widest-pair-of-indices-with-equal-range-sum/
 */
public class WidestPairOfIndicesWithEqualRangeSum {

    // time O(n), space O(n)
    public int widestPairOfIndices(int[] nums1, int[] nums2) {
        // 用数组代替哈希表来优化
        int res = 0, n = nums1.length;
        for (int i = 0; i < n; i++) {
            nums1[i] -= nums2[i];
        }
        int[] map = new int[(n << 1) + 1];
        Arrays.fill(map, n);
        map[n] = -1;
        for (int i = 0, sum = 0; i < n; i++) {
            sum += nums1[i];
            if (map[sum + n] != n) {
                res = Math.max(res, i - map[sum + n]);
            } else {
                map[sum + n] = i;
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public int widestPairOfIndicesPrefixSumHashMap(int[] nums1, int[] nums2) {
        // 把数组中的值修改为 nums1[i] - nums[i]，则题目转换为求和为0的最长子数组。
        int res = 0, n = nums1.length;
        for (int i = 0; i < n; i++) {
            nums1[i] -= nums2[i];
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0, sum = 0; i < n; i++) {
            sum += nums1[i];
            if (map.containsKey(sum)) {
                res = Math.max(res, i - map.get(sum));
            }
            map.putIfAbsent(sum, i);
        }
        return res;
    }
}
