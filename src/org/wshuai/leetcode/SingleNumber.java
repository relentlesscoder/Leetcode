package org.wshuai.leetcode;

/**
 * Created by Wei on 9/19/2016.
 * #136 https://leetcode.com/problems/single-number/
 */
public class SingleNumber {
  public int singleNumber(int[] nums) {
    int len = nums.length;
    int x = nums[0];
    for(int i = 1; i < len; i++){
      x ^= nums[i];
    }
    return x;
  }
}
