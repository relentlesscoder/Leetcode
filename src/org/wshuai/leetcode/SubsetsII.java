package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/5/16.
 * #90 https://leetcode.com/problems/subsets-ii/
 */
public class SubsetsII {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> lst = new ArrayList<List<Integer>>();
    lst.add(new ArrayList<Integer>());
    Arrays.sort(nums);
    getAllSubsets(nums, 0, lst, new ArrayList<Integer>());
    return lst;
  }

  private void getAllSubsets(int[] nums, int idx, List<List<Integer>> lst, List<Integer> last){
    int len = nums.length;
    if(idx == len){
      return;
    }
    for(int i = idx; i < len; i++){
      last.add(nums[i]);
      lst.add(new ArrayList<Integer>(last));
      getAllSubsets(nums, i+1, lst, last);
      last.remove(last.size()-1);
      while(i < len-1 && nums[i] == nums[i+1]){
        i++;
      }
    }
  }
}
