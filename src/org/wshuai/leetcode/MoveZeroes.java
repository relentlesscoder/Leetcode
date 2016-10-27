package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #283 https://leetcode.com/problems/move-zeroes/
 */
public class MoveZeroes {
  public void moveZeroes(int[] nums) {
    if(nums == null || nums.length == 0){
      return;
    }
    int i = -1;
    int len = nums.length;
    for(int j = 0; j < len; j++){
      if(nums[j] != 0){
        i++;
        nums[i] = nums[j];
      }
    }
    for(int j = i + 1; j < len; j++){
      nums[j] = 0;
    }
  }
}
