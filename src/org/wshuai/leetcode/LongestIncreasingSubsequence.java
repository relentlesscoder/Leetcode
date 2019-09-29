package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/14/16.
 * #300 https://leetcode.com/problems/longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence {
	//O(n^2), DP
  public int lengthOfLIS(int[] nums) {
    if(nums == null || nums.length == 0){
      return 0;
    }
    int max = 1;
    int[] dp = new int[nums.length];
    Arrays.fill(dp, 1);
    for(int i = 1; i < nums.length; i++){
      for(int j = i - 1; j >= 0; j--){
        if(nums[i] > nums[j]){
          dp[i] = Math.max(dp[j] + 1, dp[i]);
        }
      }
      max = Math.max(dp[i], max);
    }
    return max;
  }
}
