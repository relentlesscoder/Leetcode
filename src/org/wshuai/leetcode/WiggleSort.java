package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/31/16.
 * #280 https://leetcode.com/problems/wiggle-sort/
 */
public class WiggleSort {

  //O(n) 1ms
  public void wiggleSort(int[] nums) {
    if(nums == null || nums.length == 0){
      return;
    }
    int len = nums.length;
    for(int i = 1; i < len; i++){
      if((i % 2 == 1 && nums[i] < nums[i-1]) || (i % 2 == 0 && nums[i] > nums[i-1])){
        int temp = nums[i];
        nums[i] = nums[i-1];
        nums[i-1] = temp;
      }
    }
  }

  //O(n*log(n)) 7ms
  public void wiggleSortSwitchTwo(int[] nums) {
    if(nums == null || nums.length == 0){
      return;
    }
    int len = nums.length;
    Arrays.sort(nums);
    if(len <= 2){
      return;
    }
    for(int i = 2; i < len; i+=2){
      int temp = nums[i];
      nums[i] = nums[i-1];
      nums[i-1] = temp;
    }
  }

  //O(n^2) 41ms
  public void wiggleSortInsertion(int[] nums) {
    if(nums == null || nums.length == 0){
      return;
    }
    int len = nums.length;
    Arrays.sort(nums);
    int idx = 1;
    while(idx != -1){
      int val = nums[len-1];
      int i;
      for(i = idx; i < len; i++){
        if(nums[i] < val && nums[i-1] < val){
          idx = i;
          break;
        }
      }
      idx = i==len ? -1 : idx;
      if(idx != -1){
        int x = idx;
        while(x < len){
          int temp = nums[x];
          nums[x] = val;
          val = temp;
          x++;
        }
      }
    }
  }
}
