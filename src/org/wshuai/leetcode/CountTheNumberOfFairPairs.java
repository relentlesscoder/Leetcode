package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/16/2025.
 * #2563 https://leetcode.com/problems/count-the-number-of-fair-pairs/
 */
public class CountTheNumberOfFairPairs {

    // time O(n * log(n)), space O(log(n))
    public long countFairPairs(int[] nums, int lower, int upper) {
        int n = nums.length;
        long res = 0;
        Arrays.sort(nums);
        return lowerBound(nums, upper + 1) - lowerBound(nums, lower);
    }

    private long lowerBound(int[] nums, int target) {
        long res = 0;
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int sum = nums[low] + nums[high];
            if (sum < target) {
                res += (high - low);
                low++;
            } else {
                high--;
            }
        }
        return res;
    }

    // time O(n * log(n)), space O(log(n))
    public long countFairPairsBinarySearch(int[] nums, int lower, int upper) {
        int n = nums.length;
        long res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n - 1; i++) {
            int lowerBound = searchLower(nums, i + 1, n, lower - nums[i]),
                    upperBound = searchLower(nums, i + 1, n, upper - nums[i] + 1);
            res += (upperBound - lowerBound);
        }
        return res;
    }

    private int searchLower(int[] nums, int start, int end, int target) {
        int low = start, high = end;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        if (low == start && nums[low] < target) {
            low--;
        }
        return low;
    }
}
