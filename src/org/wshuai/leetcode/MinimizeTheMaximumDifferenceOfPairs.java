package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/08/2023.
 * #2616 https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/
 */
public class MinimizeTheMaximumDifferenceOfPairs {

    // time O(n*log(n) + n*log(m)), space O(log(n))
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int n = nums.length, left = 0, right = nums[n - 1] - nums[0];
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (countValidPairs(nums, mid) >= p) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int countValidPairs(int[] nums, int diff) {
        int i = 0, count = 0;
        while (i < nums.length - 1) {
            if (nums[i + 1] - nums[i] <= diff) {
                count++;
                i++;
            }
            i++;
        }
        return count;
    }
}
