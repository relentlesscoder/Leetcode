package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/27/2025.
 * #3026 https://leetcode.com/problems/maximum-good-subarray-sum/
 */
public class MaximumGoodSubarraySum {

    // time O(n), space O(n)
    public long maximumSubarraySum(int[] nums, int k) {
        long res = Long.MIN_VALUE, sum = 0;
        int n = nums.length;
        // 哈希表存元素值和以该元素值为结尾的最小前缀数组和
        Map<Integer, Long> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (map.containsKey(nums[i] - k)) {
                res = Math.max(res, sum - map.get(nums[i] - k) + nums[i] - k);
            }
            if (map.containsKey(nums[i] + k)) {
                res = Math.max(res, sum - map.get(nums[i] + k) + nums[i] + k);
            }
            // 只有当值在哈希表里不存在或者对应的前缀和更大才更新哈希表，因为我们的
            // 目标是找到最大好子数组和所以前缀和越小越好。
            if (!map.containsKey(nums[i]) || map.get(nums[i]) > sum) {
                map.put(nums[i], sum);
            }
        }
        return res == Long.MIN_VALUE ? 0 : res;
    }
}
