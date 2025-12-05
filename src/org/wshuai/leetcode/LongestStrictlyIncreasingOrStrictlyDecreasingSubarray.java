package org.wshuai.leetcode;

/**
 * Created by Wei on 04/06/2025.
 * #3105 https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/
 */
public class LongestStrictlyIncreasingOrStrictlyDecreasingSubarray {

    // time O(n), space O(1)
    public int longestMonotonicSubarray(int[] nums) {
        int res = 1, incLen = 1, decLen = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                incLen++;
                decLen = 1;
            } else if (nums[i] < nums[i - 1]) {
                decLen++;
                incLen = 1;
            } else {
                incLen = 1;
                decLen = 1;
            }
            res = Math.max(res, Math.max(incLen, decLen));
        }
        return res;
    }

    // time O(n), space O(1)
    public int longestMonotonicSubarrayBooleanFlag(int[] nums) {
        int res = 1, len = 1;
        boolean asc = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                len = asc ? len + 1 : 2;
                asc = true;
            } else if (nums[i] < nums[i - 1]) {
                len = asc ? 2 : len + 1;
                asc = false;
            } else {
                len = 1;
            }
            res = Math.max(res, len);
        }
        return res;
    }
}
