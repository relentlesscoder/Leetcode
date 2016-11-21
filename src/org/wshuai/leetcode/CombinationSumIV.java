package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/28/2016.
 * #377 https://leetcode.com/problems/combination-sum-iv/
 */
public class CombinationSumIV {

  //Optimal solution - http://www.cnblogs.com/grandyang/p/5705750.html
  public int combinationSum4(int[] nums, int target) {
    if(nums == null || nums.length == 0){
      return 0;
    }

    int len = nums.length;
    Arrays.sort(nums);
    int[] aux = new int[target+1];
    aux[0] = 1;
    for(int i = 1; i <= target; i++){
      for(int j = 0; j < len; j++){
        int val = nums[j];
        if(i < val){
          break;
        }
        aux[i] += aux[i-val];
      }
    }

    return aux[target];
  }

  //Dynamic Programming
  public int combinationSum4DP(int[] nums, int target) {
    if(nums == null || nums.length == 0){
      return 0;
    }

    int len = nums.length;
    int[] aux = new int[target+1];
    aux[0] = 1;
    for(int i = 1; i <= target; i++){
      for(int j = 0; j < len; j++){
        int val = nums[j];
        if(i >= val){
          aux[i] += aux[i-val];
        }
      }
    }

    return aux[target];
  }

  // TLE
  public int combinationSum4DFS(int[] nums, int target) {
    if(nums == null || nums.length == 0){
      return 0;
    }
    RefType rt = new RefType();
    int len = nums.length;
    combinationSum4DFSUtil(nums, rt, len, target, 0);
    return rt.val;
  }

  private void combinationSum4DFSUtil(int[] nums, RefType rt, int len, int target, int sum){
    if(sum > target){
      return;
    }
    if(sum == target){
      rt.val++;
      return;
    }else{
      for(int i = 0; i < len; i++){
        combinationSum4DFSUtil(nums, rt, len, target, sum + nums[i]);
      }
    }
  }
}
