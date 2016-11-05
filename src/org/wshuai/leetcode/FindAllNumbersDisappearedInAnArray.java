package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/3/16.
 * #448 https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 */
public class FindAllNumbersDisappearedInAnArray {
  //O(n) time, in-place bucket sort
  public List<Integer> findDisappearedNumbers(int[] nums) {
    List<Integer> lst = new ArrayList<Integer>();
    if(nums == null || nums.length == 0){
      return lst;
    }
    int len = nums.length;
    for(int i = 0; i < len; i++){
      if(nums[i] == i+1){
        continue;
      }
      while(nums[i] != i+1 && nums[i] != nums[nums[i]-1]){
        int temp = nums[i];
        nums[i] = nums[temp-1];
        nums[temp-1] = temp;
      }
    }
    for(int i = 0; i < len; i++){
      if(nums[i] != i+1){
        lst.add(i+1);
      }
    }
    return lst;
  }
}
