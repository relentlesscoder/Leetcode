package org.wshuai.leetcode;

/**
 * Created by Wei on 7/28/2017.
 * #645 https://leetcode.com/problems/maximum-average-subarray-i/
 */
public class MaximumAverageSubarrayI {
  public double findMaxAverage(int[] nums, int k) {
    //https://stackoverflow.com/questions/3884793/why-is-double-min-value-in-not-negative
    double max = -Double.MAX_VALUE;
    int sum = 0;
    int len = nums.length;
    for(int i = 0; i < len; i++){
      sum += nums[i];
      if(i >= k-1){
        if(i >= k){
          sum -= nums[i-k];
        }
        double avg = ((double)sum)/((double)k);
        max = Math.max(avg, max);
      }
    }
    return max;
  }
}
