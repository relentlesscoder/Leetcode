package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/16/2025.
 * #2563 https://leetcode.com/problems/count-the-number-of-fair-pairs/
 */
public class CountTheNumberOfFairPairs {

    // time O(n * log(n)), space O(1)
    public long countFairPairs(int[] nums, int lower, int upper) {
        // lower <= x <= upper
        // x = (<= upper) - (< lower)
        // (<= upper) = (< upper + 1)
        // x = (< upper + 1) - (< lower)
        Arrays.sort(nums);
        return countPairs(nums, upper + 1) - countPairs(nums, lower);
    }

    private long countPairs(int[] nums, int target) {
        long res = 0;
        int n = nums.length;
        for (int i = 0, j = n - 1; i < j; ) {
            if (nums[i] + nums[j] < target) {
                res += j - i;
                i++;
            } else {
                j--;
            }
        }
        return res;
    }

    // time O(n * log(n)), space O(1)
    public long countFairPairsBinarySearch(int[] nums, int lower, int upper) {
        long res = 0;
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 1; i++) {
            int lowCount = n - binarySearch(nums, i + 1, n, lower - nums[i]);
            int highCount = n - binarySearch(nums, i + 1, n, upper + 1 - nums[i]);
            res += lowCount - highCount;
        }
        return res;
    }

    private int binarySearch(int[] nums, int low, int high, int target) {
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
