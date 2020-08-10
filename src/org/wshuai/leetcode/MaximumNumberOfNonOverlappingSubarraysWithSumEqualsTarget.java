package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/10/2020.
 * #1546 https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/
 */
public class MaximumNumberOfNonOverlappingSubarraysWithSumEqualsTarget {

    // time O(n), space O(n)
    public int maxNonOverlapping(int[] nums, int target) {
        int res = 0, n = nums.length, sum = 0;
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0);
        for(int i = 0; i < n; i++){
            sum += nums[i];
            if(dp.containsKey(sum - target)){
                res = Math.max(res, dp.get(sum - target) + 1);
            }
            dp.put(sum, res);
        }
        return res;
    }

    // time O(n^2), space O(n)
    public int maxNonOverlappingTLE(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        for(int i = 0; i < n; i++){
            dp[i + 1] = dp[i];
            // can be optimized by prefix sum
            for(int sum = 0, j = i; j >= 0; j--){
                sum += nums[j];
                if(sum == target){
                    dp[i + 1] = Math.max(dp[i + 1], dp[j] + 1);
                    break;
                }
            }
        }
        return dp[n];
    }
}
