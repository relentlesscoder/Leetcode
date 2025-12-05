package org.wshuai.leetcode;

/**
 * Created by Wei on 01/07/2024.
 * #2439 https://leetcode.com/problems/minimize-maximum-of-array/
 */
public class MinimizeMaximumOfArray {

    // time O(n), space O(1)
    public int minimizeArrayValue(int[] nums) {
        // To minimize the maximum, we need to get the average for the prefix subarray from 0 to each current position i.
        // Case 1: for prefix subarray [96, 1, 2 ... ], the average of subarray [0, 2] is not reachable, but it is hided by
        // that of subarray [0, 0].
        // Case 2: for prefix subarray [1, 2, 96 ... ], the average 33 of subarray [0, 2] is valid since we move the value
        // from right to left.
        long max = -1, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, (sum + i) / (i + 1));
        }
        return (int)max;
    }
}
