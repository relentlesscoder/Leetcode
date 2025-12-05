package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/12/2023.
 * #2090 https://leetcode.com/problems/k-radius-subarray-averages/
 */
public class KRadiusSubarrayAverages {

    // time O(n), space O(1)
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length, m = 2 * k + 1;
        long sum = 0;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (i - m + 1 < 0) {
                continue;
            }
            res[i - k] = (int) (sum / m);
            sum -= nums[i - m + 1];
        }
        return res;
    }
}
