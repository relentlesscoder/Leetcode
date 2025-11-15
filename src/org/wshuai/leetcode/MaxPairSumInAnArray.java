package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/26/2023.
 * #2815 https://leetcode.com/problems/max-pair-sum-in-an-array/
 */
public class MaxPairSumInAnArray {

    // time O(n * log(MAX)), space O(1)
    public int maxSum(int[] nums) {
        int res = -1, n = nums.length;
        int[] map = new int[10];
        Arrays.fill(map, -1);
        for (int i = 0; i < n; i++) {
            int val = nums[i], digit = 0;
            // Find the max digit for nums[i]
            while (val > 0) {
                digit = Math.max(digit, val % 10);
                val /= 10;
            }
            if (map[digit] != -1) {
                res = Math.max(res, nums[i] + map[digit]);
            }
            // Update map[digit] to record the maximum number
            // that has digit as its largest digit
            map[digit] = Math.max(map[digit], nums[i]);
        }
        return res;
    }
}
