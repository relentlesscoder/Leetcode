package org.wshuai.leetcode;

/**
 * Created by Wei on 10/5/16.
 * #55 https://leetcode.com/problems/jump-game/
 */
public class JumpGame {
  public boolean canJump(int[] nums) {
    if(nums == null){
      throw new IllegalArgumentException("Invalid input.");
    }

    int max = 0;
    int i = 0;
    int len = nums.length;
    //"i <= max" makes sure it can jump to i
    for(i = 0; i <= max && i < len; i++){
      max = Math.max(max, i+nums[i]);
    }
    return i == len;
  }
}
