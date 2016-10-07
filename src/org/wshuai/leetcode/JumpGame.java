package org.wshuai.leetcode;

/**
 * Created by Wei on 10/5/16.
 */
public class JumpGame {
  public boolean canJump(int[] nums) {
    if(nums == null){
      throw new IllegalArgumentException("Invalid input.");
    }

    int max = 0;
    int i = 0;
    int len = nums.length;
    for(i = 0; i <= max && i < len; i++){
      max = Math.max(max, i+nums[i]);
    }
    return i == len;
  }
}
