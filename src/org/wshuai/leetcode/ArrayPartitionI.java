package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 7/6/2017.
 * #561 https://leetcode.com/problems/array-partition-i/
 */
public class ArrayPartitionI {
  public int arrayPairSum(int[] nums) {
    Arrays.sort(nums);
    int sum = 0;
    for(int i = 0; i < nums.length; i+=2){
      sum += nums[i];
    }
    return sum;
  }
}
