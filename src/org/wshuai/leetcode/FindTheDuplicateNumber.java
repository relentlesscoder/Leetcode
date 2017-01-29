package org.wshuai.leetcode;

/**
 * Created by Wei on 1/29/17.
 * #287 https://leetcode.com/problems/find-the-duplicate-number/
 */
public class FindTheDuplicateNumber {
  public int findDuplicate(int[] nums) {
    if(nums == null || nums.length == 0){
      return -1;
    }
    int len = nums.length;
    for(int i = 0; i < len; i++){
      while(nums[i] != i+1 && nums[nums[i]-1] != nums[i]){
        int val = nums[i];
        nums[i] = nums[val-1];
        nums[val-1] = val;
      }
    }
    for(int i = 0; i < len; i++){
      if(nums[i] != i+1){
        return nums[i];
      }
    }
    return -1;
  }
}
