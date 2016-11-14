package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2016.
 * #209 https://leetcode.com/problems/minimum-size-subarray-sum/
 */
public class MinimumSizeSubarraySum {

  //O(n), same idea as https://leetcode.com/problems/minimum-window-substring/
  public int minSubArrayLen(int s, int[] nums) {
    if(nums == null || nums.length == 0){
      return 0;
    }
    int min = Integer.MAX_VALUE;
    int len = nums.length;
    int sum = 0;
    int j = 0;
    for(int i = 0; i < len; i++){
      sum += nums[i];
      while(sum-nums[j] >= s){
        sum -= nums[j];
        j++;
      }
      if(sum >= s){
        int cLen = i-j+1;
        min = cLen < min ? cLen : min;
      }
    }
    return min == Integer.MAX_VALUE ? 0 : min;
  }
}
