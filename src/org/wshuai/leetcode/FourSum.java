package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/25/16.
 * #18 https://leetcode.com/problems/4sum/
 */
public class FourSum {
  public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> lst = new ArrayList<List<Integer>>();
    if(nums == null || nums.length == 0){
      return lst;
    }

    Arrays.sort(nums);
    int len = nums.length;
    for(int i = 0; i < len - 3; i++){
      int first = nums[i];
      if(i != 0 && first == nums[i - 1]){
        continue;
      }
      for(int j = i + 1; j < len - 2; j++){
        int second = nums[j];
        if(j != i + 1 && second == nums[j - 1]){
          continue;
        }
        int l = j + 1;
        int r = len - 1;
        while(l < r){
          int third = nums[l];
          int fouth = nums[r];
          int sum = first + second + third + fouth;
          if(sum == target){
            List<Integer> ls = new ArrayList<Integer>();
            ls.add(first);
            ls.add(second);
            ls.add(third);
            ls.add(fouth);
            lst.add(ls);
            l++;
            r--;

            while(l < r && nums[l] == third){
              l++;
            }
            while(l < r && nums[r] == fouth){
              r--;
            }
          }
          else if(sum < target){
            l++;
          }else{
            r--;
          }
        }
      }
    }
    return lst;
  }
}
