package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/03/2025.
 * #3194 https://leetcode.com/problems/minimum-average-of-smallest-and-largest-elements/
 */
public class MinimumAverageOfSmallestAndLargestElements {

    // time O(n * log(n)), space O(log(n))
    public double minimumAverage(int[] nums) {
        int minSum = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0, j = nums.length - 1; j > i; i++, j--) {
            minSum = Math.min(minSum, nums[i] + nums[j]);
        }
        return minSum / 2.0;
    }
}
