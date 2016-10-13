package org.wshuai.leetcode;

/**
 * Created by Wei on 10/8/16.
 */
public class FirstMissingPositive {
  public int firstMissingPositive(int[] nums) {
    if(nums == null || nums.length == 0){
      return 1;
    }

    int len = nums.length;
    for(int i = 0; i < len; i++){
      while(nums[i] != i+1){
        if(nums[i] <= 0 || nums[i] >= len){
          break;
        }

        //Handle duplicates
        if(nums[i] == nums[nums[i]-1]){
          break;
        }

        int temp = nums[i];
        nums[i] = nums[temp-1];
        nums[temp-1] = temp;
      }
    }

    for(int i = 0; i < len; i++){
      if(nums[i] != i+1){
        return i+1;
      }
    }
    return len+1;
  }
}
