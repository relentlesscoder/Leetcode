package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/09/2019.
 * #1099 https://leetcode.com/problems/two-sum-less-than-k/
 */
public class TwoSumLessThanK {

    // time O(n * log(n)), space O(log(n))
    public int twoSumLessThanK(int[] nums, int k) {
        int n = nums.length, res = -1;
        Arrays.sort(nums);
        for (int i = 0, j = n - 1; i < j; ) {
            int sum = nums[i] + nums[j];
            if (sum < k) {
                res = Math.max(res, sum);
                i++;
            } else {
                j--;
            }
        }
        return res;
    }
}
