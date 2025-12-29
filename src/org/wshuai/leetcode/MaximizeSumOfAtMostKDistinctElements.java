package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/10/2025.
 * #3684 https://leetcode.com/problems/maximize-sum-of-at-most-k-distinct-elements/
 */
public class MaximizeSumOfAtMostKDistinctElements {

    // time O(n * log(n)), space O(log(n) + k)
    public int[] maxKDistinct(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int j = n - 2;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] != nums[i + 1]) {
                nums[j--] = nums[i];
            }
        }
        int[] res = new int[Math.min(n - j - 1, k)];
        for (int i = 0; i < res.length; i++) {
            res[i] = nums[n - 1 - i];
        }
        return res;
    }
}
