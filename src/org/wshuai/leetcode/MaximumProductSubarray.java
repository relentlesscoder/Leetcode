package org.wshuai.leetcode;

/**
 * Created by Wei on 10/14/2016.
 * #152 https://leetcode.com/problems/maximum-product-subarray/
 */
public class MaximumProductSubarray {
  public int maxProduct(int[] nums) {
    int max = nums[0];
    int len = nums.length;
    int[] maxs = new int[len];
    int[] mins = new int[len];
    maxs[0] = mins[0] = nums[0];

    for(int i = 1; i < len; i++){
      int val = nums[i];
      if(val > 0){
        maxs[i] = Math.max(val, val*maxs[i - 1]);
        mins[i] = Math.min(val, val*mins[i - 1]);
      }else{
        maxs[i] = Math.max(val, val*mins[i - 1]);
        mins[i] = Math.min(val, val*maxs[i - 1]);
      }
      max = Math.max(max, maxs[i]);
    }

    return max;
  }
}
