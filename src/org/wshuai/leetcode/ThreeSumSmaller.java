package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/28/2016.
 * #259 https://leetcode.com/problems/3sum-smaller/
 */
public class ThreeSumSmaller {
  public int threeSumSmaller(int[] nums, int target) {
    if(nums == null || nums.length == 0){
      return 0;
    }

    Arrays.sort(nums);
    int count = 0;
    int len = nums.length;
    for(int i = 0; i < len-2; i++){
      int left = i+1;
      int right = len-1;
      while(left < right){
        int sum  = nums[i]+nums[left]+nums[right];
        if(sum >= target){
          right--;
        }else{
          //right is the maximum val, if left+right < target
          //then all values between+left < target
          count += right-left;
          left++;
        }
      }
    }
    return count;
  }
}
