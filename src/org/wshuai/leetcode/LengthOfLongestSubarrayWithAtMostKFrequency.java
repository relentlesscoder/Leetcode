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
        Map<Integer, Integer> freq = new HashMap<>();
        int max = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
            freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1);
            while (freq.get(nums[j]) > k) {
                freq.put(nums[i], freq.get(nums[i]) - 1);
                i++;
            }
            max = Math.max(max, j - i + 1);
        }
        return max;
    }
}
