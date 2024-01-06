package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/06/2024.
 * #1984 https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/
 */
public class MinimumDifferenceBetweenHighestAndLowestOfKScores {

    // time O(n * log(n)), space O(log(n))
    public int minimumDifference(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (i - j + 1 == k) {
                min = Math.min(min, nums[i] - nums[j++]);
            }
        }
        return min;
    }
}
