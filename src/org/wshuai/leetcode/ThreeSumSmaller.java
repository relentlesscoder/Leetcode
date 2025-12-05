package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/28/2016.
 * #0259 https://leetcode.com/problems/3sum-smaller/
 */
public class ThreeSumSmaller {

    // time O(n^2), space O(log(n))
    public int threeSumSmaller(int[] nums, int target) {
        int res = 0, n = nums.length;
        Arrays.sort(nums);
        for (int k = 0; k < n - 2; k++) {
            if (nums[k] + nums[k + 1] + nums[k + 2] >= target) {
                break;
            }
            if (nums[k] + nums[n - 2] + nums[n - 1] < target) {
                res += (n - k - 2) * (n - k - 1) / 2;
                continue;
            }
            for (int i = k + 1, j = n - 1; i < j; ) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < target) {
                    res += (j - i);
                    i++;
                } else {
                    j--;
                }
            }
        }
        return res;
    }
}
