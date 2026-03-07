package org.wshuai.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/02/2016.
 * #0325 https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
 */
public class MaximumSizeSubarraySumEqualsK {

    // time O(n), space O(n)
    public int maxSubArrayLen(int[] nums, int k) {
        int res = 0, n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0, sum = 0; i < n; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                res = Math.max(res, i - map.get(sum - k));
            }
            map.putIfAbsent(sum, i);
        }
        return res;
    }
}
