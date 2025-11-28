package org.wshuai.leetcode;

/**
 * Created by Wei on 07/28/2017.
 * #0645 https://leetcode.com/problems/maximum-average-subarray-i/
 */
public class MaximumAverageSubarrayI {

    // time O(n), space O(1)
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length, max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (i - k + 1 < 0) {
                continue;
            }
            max = Math.max(max, sum);
            sum -= nums[i - k + 1];
        }
		return (double) max / k;
    }

    // time O(n), space O(1)
    public double findMaxAverageDoubleCalculation(int[] nums, int k) {
        // https://stackoverflow.com/questions/3884793/why-is-double-min-value-in-not-negative
        double res = -Double.MAX_VALUE, sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (i - k + 1 < 0) {
                continue;
            }
            res = Math.max(res, sum / k);
            sum -= nums[i - k + 1];
        }
        return res;
    }
}
