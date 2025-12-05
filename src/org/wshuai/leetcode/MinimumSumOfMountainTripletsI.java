package org.wshuai.leetcode;

/**
 * Created by Wei on 01/07/2024.
 * #2908 https://leetcode.com/problems/minimum-sum-of-mountain-triplets-i/
 */
public class MinimumSumOfMountainTripletsI {

    // time O(n), space O(n)
    public int minimumSum(int[] nums) {
        // For each element nums[i], find the minimum value that are less than nums[i] on both sides,
        // the answer is the minimum of all the valid triplets.
        int res = Integer.MAX_VALUE, n = nums.length, left = nums[0];
        int[] leftMins = new int[n];
        leftMins[0] = -1;
        for (int i = 1; i < n; i++) {
            leftMins[i] = nums[i] > left ? left : -1;
            left = Math.min(left, nums[i]);
        }
        int right = nums[n - 1];
        for (int i = n - 2; i >= 1; i--) {
            if (nums[i] > right && leftMins[i] != -1) {
                res = Math.min(res, right + nums[i] + leftMins[i]);
            }
            right = Math.min(right, nums[i]);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
