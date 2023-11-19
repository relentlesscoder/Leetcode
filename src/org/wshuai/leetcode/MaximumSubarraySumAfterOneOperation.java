package org.wshuai.leetcode;

/**
 * Created by Wei on 02/25/2021.
 * #1746 https://leetcode.com/problems/maximum-subarray-sum-after-one-operation/
 */
public class MaximumSubarraySumAfterOneOperation {

    // time O(n), space O(n)
    public int maxSumAfterOperation(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0] * nums[0];
        int res = dp[0][1];
        for(int i = 1; i < n; i++){
            int prod = nums[i] * nums[i];
            dp[i][0] = Math.max(nums[i], dp[i - 1][0] + nums[i]);
            dp[i][1] = Math.max(prod, Math.max(dp[i - 1][0] + prod, dp[i - 1][1] + nums[i]));
            res = Math.max(res, dp[i][1]);
        }
        return res;
    }

    // time O(n)
    public int maxSumAfterOperationSpaceOptimized(int[] nums) {
        int n = nums.length, dp0 = nums[0], dp1 = nums[0]*nums[0];
        int res = dp1;
        for(int i = 1; i < n; i++){
            int prod = nums[i] * nums[i];
            int cur0 = Math.max(nums[i], dp0 + nums[i]);
            int cur1 = Math.max(prod, Math.max(dp0 + prod, dp1 + nums[i]));
            res = Math.max(res, cur1);
            dp0 = cur0;
            dp1 = cur1;
        }
        return res;
    }
}
