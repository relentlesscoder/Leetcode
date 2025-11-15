package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2023.
 * #2016 https://leetcode.com/problems/maximum-difference-between-increasing-elements/
 */
public class MaximumDifferenceBetweenIncreasingElements {

    // time O(n), space O(1)
    public int maximumDifference(int[] nums) {
        int res = -1, min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = Math.max(res, nums[i] > min ? nums[i] - min : -1);
            min = Math.min(min, nums[i]);
        }
        return res;
    }
}
