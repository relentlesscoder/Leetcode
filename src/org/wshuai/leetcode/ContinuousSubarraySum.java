package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 03/05/2017.
 * #0523 https://leetcode.com/problems/continuous-subarray-sum/
 */
public class ContinuousSubarraySum {

    // time O(n), space O(k)
    public boolean checkSubarraySum(int[] nums, int k) {
        // #0974相似题
        // 性质：如果两数与k取模的结果相同，则它们的差可以整除k
        int n = nums.length;
        // k的值的上限太大，否者可以用数组取代哈希表来优化
        // 哈希表的键是前缀和与k取模的结果，而值为此键在数组中的第一个的
        // 索引。因为更大的数组更有可能满足要求，所以只保留最早得到的索引。
        Map<Integer, Integer> idx = new HashMap<>();
        idx.put(0, -1);
        for (int i = 0, sum = 0; i < n; i++) {
            sum += nums[i];
            // 不用考虑负数
            int mod = sum % k, last = idx.getOrDefault(mod, n);
            if (i - last > 1) {
                return true;
            }
            // 只更新哈希表如果对应的键不在表中。
            idx.putIfAbsent(mod, i);
        }
        return false;
    }
}
