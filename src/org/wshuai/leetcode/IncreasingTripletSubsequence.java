package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2016.
 * #334 https://leetcode.com/problems/increasing-triplet-subsequence/
 */
public class IncreasingTripletSubsequence {
  //O(n)
  public boolean increasingTriplet(int[] nums) {
    if(nums == null || nums.length < 3){
      return false;
    }
    int first = Integer.MAX_VALUE;
    int sec = Integer.MAX_VALUE;
    int len = nums.length;
    for(int i = 0; i < len; i++){
      if(nums[i] <= first){
        first = nums[i];
      }else if(nums[i] <= sec){
        sec = nums[i];
      }else{
        return true;
      }
    }
    return false;
  }
}
