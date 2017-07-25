package org.wshuai.leetcode;

/**
 * Created by Wei on 7/25/2017.
 * #645 https://leetcode.com/problems/set-mismatch/
 */
public class SetMismatch {
  //O(n), bucket sort
  public int[] findErrorNums(int[] nums) {
    int[] res = new int[2];
    for(int i = 0; i < nums.length; i++){
      if(nums[i] == i+1){
        continue;
      }
      while(nums[i] != i+1){
        int idx = nums[i]-1;
        if(nums[idx] == nums[i]){
          res[0] = nums[i];
          break;
        }
        int temp = nums[i];
        nums[i] = nums[idx];
        nums[idx] = temp;
      }
    }
    for(int i = 0; i < nums.length; i++){
      if(nums[i] != i+1){
        res[1] = i+1;
        break;
      }
    }
    return res;
  }
}
