package org.wshuai.leetcode;

/**
 * Created by Wei on 08/02/2025.
 * #2419 https://leetcode.com/problems/longest-subarray-with-maximum-bitwise-and/
 */
public class LongestSubarrayWithMaximumBitwiseAnd {

    // time O(n), space O(1)
    public int longestSubarrayOnePass(int[] nums) {
        int res = 0, max = nums[0], curr = nums[0], count = 0;
        for (int num : nums) {
            if (num == curr) {
                count++;
            } else {
                curr = num;
                count = 1;
            }
            if (num >= max) {
                res = num > max ? count : Math.max(res, count);
                max = num;
            }
        }
        return res;
    }

    // time O(n), space O(1)
    public int longestSubarray(int[] nums) {
        int res = 0, max = -1, count = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        for (int num : nums) {
            if (num == max) {
                res = Math.max(res, ++count);
            } else {
                count = 0;
            }
        }
        return res;
    }
}
