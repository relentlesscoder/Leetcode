package org.wshuai.leetcode;

/**
 * Created by Wei on 11/15/2025.
 * #2874 https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-ii/
 */
public class MaximumValueOfAnOrderedTripletII {

    // time O(n), space O(1)
    public long maximumTripletValue(int[] nums) {
        long res = 0, maxDiff = 0, leftMax = 0;
        int n = nums.length;
        for (int num : nums) {
            res = Math.max(res, maxDiff * num);
            maxDiff = Math.max(maxDiff, leftMax - num);
            leftMax = Math.max(leftMax, num);
        }
        return res;
    }
}
