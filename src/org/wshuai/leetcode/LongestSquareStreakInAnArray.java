package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/16/2025.
 * #2501 https://leetcode.com/problems/longest-square-streak-in-an-array/
 */
public class LongestSquareStreakInAnArray {

    // time O(n * log(n)), space O(n)
    public int longestSquareStreak(int[] nums) {
        int max = 0;
        Arrays.sort(nums);
        // dp[i] denotes the length of longest subsequence ends with i
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sqrt = (int) Math.sqrt(nums[i]);
            if (sqrt * sqrt == nums[i]) {
                int len = Math.max(map.getOrDefault(sqrt, 0) + 1,
                        map.getOrDefault(nums[i], 0));
                max = Math.max(max, len);
                map.put(nums[i], len);
            } else {
                map.put(nums[i], 1);
            }
        }
        return max >= 2 ? max : -1;
    }
}
