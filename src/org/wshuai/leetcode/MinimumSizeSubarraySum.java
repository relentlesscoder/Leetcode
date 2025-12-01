package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2016.
 * #0209 https://leetcode.com/problems/minimum-size-subarray-sum/
 */
public class MinimumSizeSubarraySum {

    // time O(n), space O(1)
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length, res = n + 1;
        for (int i = 0, j = 0, sum = 0; i < n; i++) {
            sum += nums[i];
            while (sum >= target) {
                res = Math.min(res, i - j + 1);
                sum -= nums[j++];
            }
        }
        return res > n ? 0 : res;
    }

    // time O(n), space O(1)
    public int minSubArrayLen1(int target, int[] nums) {
        int n = nums.length, res = n + 1;
        for (int i = 0, j = 0, sum = 0; i < n; i++) {
            sum += nums[i];
            while (sum - nums[j] >= target) {
                sum -= nums[j++];
            }
            if (sum >= target) {
                res = Math.min(res, i - j + 1);
            }
        }
        return res > n ? 0 : res;
    }
}
