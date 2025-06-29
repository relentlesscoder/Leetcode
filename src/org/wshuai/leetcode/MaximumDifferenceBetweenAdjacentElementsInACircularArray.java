package org.wshuai.leetcode;

/**
 * Created by Wei on 06/29/2025.
 * #3423 https://leetcode.com/problems/maximum-difference-between-adjacent-elements-in-a-circular-array/
 */
public class MaximumDifferenceBetweenAdjacentElementsInACircularArray {

    // time O(n), space O(1)
    public int maxAdjacentDistance(int[] nums) {
        int res = 0, n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            res = Math.max(res, nums[i] > nums[i + 1] ? nums[i] - nums[i + 1] : nums[i + 1] - nums[i]);
        }
        return Math.max(res, nums[0] > nums[n - 1] ? nums[0] - nums[n - 1] : nums[n - 1] - nums[0]);
    }
}
