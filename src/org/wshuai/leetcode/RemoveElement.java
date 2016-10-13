package org.wshuai.leetcode;

/**
 * Created by Wei on 10/10/16.
 * #27 https://leetcode.com/problems/remove-element/
 */
public class RemoveElement {
  public int removeElement(int[] nums, int val) {
    if(nums == null || nums.length == 0){
      return 0;
    }
    int i = -1;
    for(int j = 0; j < nums.length; j++){
      if(nums[j] != val){
        i++;
        nums[i] = nums[j];
      }
    }
    return i+1;
  }
}
