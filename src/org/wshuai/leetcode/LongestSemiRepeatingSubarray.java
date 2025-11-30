package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/30/2025.
 * #3641 https://leetcode.com/problems/longest-semi-repeating-subarray/
 */
public class LongestSemiRepeatingSubarray {

    // time O(n), space O(MAX)
    public int longestSubarray(int[] nums, int k) {
        int res = 0, n = nums.length;
        int[] freq = new int[100_001];
        for (int i = 0, j = 0, repeats = 0; i < n; i++) {
            if (freq[nums[i]]++ == 1) {
                repeats++;
            }
            while (repeats > k) {
                if (--freq[nums[j++]] == 1) {
                    repeats--;
                }
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    // time O(n), space O(n)
    public int longestSubarrayHashMap(int[] nums, int k) {
        int res = 0, n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0, j = 0, repeats = 0; i < n; i++) {
            if (freq.merge(nums[i], 1, Integer::sum) == 2) {
                repeats++;
            }
            while (repeats > k) {
                if (freq.merge(nums[j++], -1, Integer::sum) == 1) {
                    repeats--;
                }
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
