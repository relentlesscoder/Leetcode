package org.wshuai.leetcode;

/**
 * Created by Wei on 11/17/2023.
 * #2529 https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/
 */
public class MaximumCountOfPositiveIntegerAndNegativeInteger {

    // time O(log(n)), space O(1)
    public int maximumCount(int[] nums) {
        int positive = nums.length - binarySearch(nums, 1);
        int negative = binarySearch(nums, 0);
        return Math.max(positive, negative);
    }

    private int binarySearch(int[] nums, int target) {
        int n = nums.length, low = 0, high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
