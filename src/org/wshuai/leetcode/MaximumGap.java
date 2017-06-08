package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 6/7/2017.
 * #164 https://leetcode.com/problems/maximum-gap/
 */
public class MaximumGap {
  public int maximumGap(int[] nums) {
    if(nums == null || nums.length <= 1){
      return 0;
    }

    int len = nums.length;
    int min = nums[0];
    int max = nums[0];
    for(int i: nums){
      min = Math.min(min, i);
      max = Math.max(max, i);
    }

    int gap = (int)Math.ceil((double)(max-min)/(len-1));
    int[] bucketMin = new int[len-1];
    int[] bucketMax = new int[len-1];
    Arrays.fill(bucketMin, Integer.MAX_VALUE);
    Arrays.fill(bucketMax, Integer.MIN_VALUE);

    for(int i: nums){
      if(i == min || i == max){
        continue;
      }
      int idx = (i-min)/gap;
      bucketMin[idx] = Math.min(i, bucketMin[idx]);
      bucketMax[idx] = Math.max(i, bucketMax[idx]);
    }

    int maxGap = Integer.MIN_VALUE;
    int previous = min;
    for(int i = 0; i < len-1; i++){
      if(bucketMin[i] == Integer.MAX_VALUE && bucketMax[i] == Integer.MIN_VALUE){
        continue;
      }
      maxGap = Math.max(maxGap, bucketMin[i] - previous);
      previous = bucketMax[i];
    }
    maxGap = Math.max(maxGap, max-previous);
    return maxGap;
  }
}
