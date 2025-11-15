package org.wshuai.leetcode;

/**
 * Created by Wei on 06/22/2025.
 * #2873 https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-i/
 */
public class MaximumValueOfAnOrderedTripletI {

    // time O(n), space O(1)
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long res = 0, leftMax = 0, maxDiff = 0;
        for (int i = 0; i < n; i++) {
            // Maintain max value for (nums[i] - nums[j]) * nums[k]
            res = Math.max(res, maxDiff * nums[i]);
            // Maintain max value for nums[i] - nums[j]
            maxDiff = Math.max(maxDiff, leftMax - nums[i]);
            // Maintain max value for nums[i]
            leftMax = Math.max(leftMax, nums[i]);
        }
        return res;
    }

    // time O(n), space O(n)
    public long maximumTripletValueTwoScans(int[] nums) {
        int n = nums.length;
        int[] leftMax = new int[n], rightMax = new int[n];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], nums[i - 1]);
            rightMax[n - i - 1] = Math.max(rightMax[n - i], nums[n - i]);
        }
        long res = 0;
        for (int i = 1; i < n - 1; i++) {
            res = Math.max(res, (long) (leftMax[i] - nums[i]) * rightMax[i]);
        }
        return res;
    }
}
