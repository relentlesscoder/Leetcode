package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/10/16.
 */
public class ContainsDuplicate {
  public boolean containsDuplicate(int[] nums) {
    if(nums == null || nums.length < 2){
      return false;
    }

    int len = nums.length;
    Set<Integer> s = new HashSet<Integer>();
    for(int i = 0; i < len; i++){
      if(!s.add(nums[i])){
        return true;
      }
    }
    return false;
  }
}
