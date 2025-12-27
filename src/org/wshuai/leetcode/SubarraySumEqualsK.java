package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/05/2019.
 * #0560 https://leetcode.com/problems/subarray-sum-equals-k/
 */
public class SubarraySumEqualsK {

    // time O(n), space O(n)
    public int subarraySum(int[] nums, int k) {
        int res = 0, n = nums.length;
        Map<Integer, Integer> prefix = new HashMap<>(); // 哈希表存前缀和的出现的次数
        prefix.put(0, 1); // 预先加入前缀和0 (前缀子数组为空)
        for (int i = 0, sum = 0; i < n; i++) {
            sum += nums[i];
            res += prefix.getOrDefault(sum - k, 0);
            prefix.merge(sum, 1, Integer::sum);
        }
        return res;
    }
}
