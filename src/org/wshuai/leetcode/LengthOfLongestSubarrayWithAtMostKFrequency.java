package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/17/2025.
 * #2958 https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/
 */
public class LengthOfLongestSubarrayWithAtMostKFrequency {

    // time O(n), space O(n)
    public int maxSubarrayLength(int[] nums, int k) {
        int res = 0, n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0, j = 0; i < n; i++) {
            freq.merge(nums[i], 1, Integer::sum);
            while (freq.get(nums[i]) > k) {
                freq.merge(nums[j++], -1, Integer::sum);
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
