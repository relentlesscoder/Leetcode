package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 1/7/2017.
 * #442 https://leetcode.com/problems/find-all-duplicates-in-an-array/
 */
public class FindAllDuplicatesInAnArray {
  //O(n), inline bucket sort
  public List<Integer> findDuplicates(int[] nums) {
    List<Integer> lst = new ArrayList<Integer>();
    if(nums == null || nums.length == 0){
      return lst;
    }
    int len = nums.length;
    for(int i = 0; i < len; i++){
      while(nums[i] != i+1 && nums[nums[i]-1] != nums[i]){
        int tmp = nums[i];
        nums[i] = nums[tmp-1];
        nums[tmp-1] = tmp;
      }
    }
    for(int i = 0; i < len; i++){
      if(nums[i] != i+1){
        lst.add(nums[i]);
      }
    }
    return lst;
  }
}
