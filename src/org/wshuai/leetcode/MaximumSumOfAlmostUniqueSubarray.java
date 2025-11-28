package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 11/28/2025.
 * #2841 https://leetcode.com/problems/maximum-sum-of-almost-unique-subarray/
 */
public class MaximumSumOfAlmostUniqueSubarray {

    // time O(n), space O(n)
    public long maxSum(List<Integer> nums, int m, int k) {
        long res = 0, sum = 0;
        int n = nums.size();
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sum += nums.get(i);
            freq.merge(nums.get(i), 1, Integer::sum);
            if (i - k + 1 < 0) {
                continue;
            }
            if (freq.size() >= m) {
                res = Math.max(res, sum);
            }
            sum -= nums.get(i - k + 1);
            int count = freq.get(nums.get(i - k + 1)) - 1;
            if (count == 0) {
                freq.remove(nums.get(i - k + 1));
            } else {
                freq.put(nums.get(i - k + 1), count);
            }
        }
        return res;
    }
}
