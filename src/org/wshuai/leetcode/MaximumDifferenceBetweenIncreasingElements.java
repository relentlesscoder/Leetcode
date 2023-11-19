package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2023.
 * #2016 https://leetcode.com/problems/maximum-difference-between-increasing-elements/description/
 */
public class MaximumDifferenceBetweenIncreasingElements {

    // time O(n), space O(1)
    public int maximumDifference(int[] nums) {
        int res = -1, min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > min) {
                res = Math.max(res, nums[i] - min);
            } else {
                min = nums[i];
            }
        }
        return res;
    }
}
