package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/28/16.
 * #368 https://leetcode.com/problems/largest-divisible-subset/
 */
public class LargestDivisibleSubset {

  //Dynamic Programming 33ms
  public List<Integer> largestDivisibleSubset(int[] nums) {
    List<Integer> lst = new ArrayList<Integer>();
    if(nums == null || nums.length == 0){
      return lst;
    }

    int len = nums.length;
    int max = 0;
    int maxIdx = -1;
    int[] maxs = new int[len];
    int[] idxs = new int[len];
    Arrays.fill(maxs, 1);
    Arrays.fill(idxs, -1);
    Arrays.sort(nums);
    for(int i = 0; i < len; i++){
      for(int j = i-1; j >= 0; j--){
        if(nums[i]%nums[j] == 0 && maxs[j]+1 > maxs[i]){
          maxs[i] = maxs[j]+1;
          idxs[i] = j;
        }
      }

      if(maxs[i] > max){
        max = maxs[i];
        maxIdx = i;
      }
    }
    for(int i = maxIdx; i != -1; i = idxs[i]){
      lst.add(nums[i]);
    }
    return lst;
  }

  List<Integer> result;

  //Depth first search - TLE
  public List<Integer> largestDivisibleSubsetDFS(int[] nums) {
    if(nums == null || nums.length == 0){
      return new ArrayList<Integer>();
    }

    List<Integer> lst = new ArrayList<Integer>();
    int[] max = new int[1];
    max[0] = 0;
    Arrays.sort(nums);
    Helper(nums, 0, max, lst);
    return result;
  }

  private void Helper(int[] nums, int start, int[] max, List<Integer> lst){
    if(lst.size() > max[0]){
      max[0] = lst.size();
      result = new ArrayList<Integer>(lst);
    }
    int len = nums.length;
    if(start == len){
      return;
    }
    for(int i = start; i < len; i++){
      if(lst.size() == 0){
        lst.add(nums[i]);
        Helper(nums, i+1, max, lst);
        lst.remove(lst.size()-1);
      }else{
        int tail = lst.get(lst.size()-1);
        if(nums[i]%tail == 0){
          lst.add(nums[i]);
          Helper(nums, i+1, max, lst);
          lst.remove(lst.size()-1);
        }
      }
    }
  }
}
