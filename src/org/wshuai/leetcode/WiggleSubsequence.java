package org.wshuai.leetcode;

/**
 * Created by Wei on 3/27/2017.
 * #376 https://leetcode.com/problems/wiggle-subsequence/
 */
public class WiggleSubsequence {
  //O(n)
  public int wiggleMaxLength(int[] nums) {
    if(nums == null || nums.length == 0){
      return 0;
    }
    int len = nums.length;
    if(len == 1){
      return 1;
    }
    for(int i = 0; i < len-1; i++){
      nums[i] = nums[i+1]-nums[i];
    }
    int max = 0;
    int prev = 0;
    for(int i = 0; i < len-1; i++){
      if(nums[i] != 0 && (prev == 0 || oppositeSign(nums[i], prev))){
        max++;
      }
      if(nums[i] != 0){
        prev = nums[i];
      }
    }
    return max == 0 ? 1 : max+1;
  }

  private boolean oppositeSign(int x, int y){
    return (x > 0 && y < 0) || (x < 0 && y > 0);
  }
}
