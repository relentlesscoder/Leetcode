package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/23/2016.
 * #462 https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/
 */
public class MinimumMovesToEqualArrayElementsII {
  public int minMoves2(int[] nums) {
    if(nums == null || nums.length == 0){
      return 0;
    }
    int len = nums.length;
    Arrays.sort(nums);
    int mid = nums[len/2];
    int min = 0;
    for(int i = 0; i < len; i++){
      min += Math.abs(nums[i]-mid);
    }
    return min;
  }
}
