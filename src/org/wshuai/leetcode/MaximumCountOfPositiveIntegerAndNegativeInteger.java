package org.wshuai.leetcode;

/**
 * Created by Wei on 11/17/2023.
 * #2529 https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/
 */
public class MaximumCountOfPositiveIntegerAndNegativeInteger {

    // time O(log(n)), space O(1)
    public int maximumCount(int[] nums) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (nums[mid] < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        int neg = low, pos = 0;
        while (low < nums.length && nums[low] == 0) {
            low++;
        }
        pos = nums.length - low;
        return Math.max(neg, pos);
    }
}
