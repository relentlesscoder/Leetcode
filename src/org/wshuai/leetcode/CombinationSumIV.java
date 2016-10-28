package org.wshuai.leetcode;

/**
 * Created by Wei on 10/28/2016.
 * #377 https://leetcode.com/problems/combination-sum-iv/
 */
public class CombinationSumIV {
  public int combinationSum4(int[] nums, int target) {
    if(nums == null || nums.length == 0){
      return 0;
    }
    ResRefType rt = new ResRefType();
    int len = nums.length;
    combinationSum4DFS(nums, rt, len, target, 0);
    return rt.cs;
  }

  private void combinationSum4DFS(int[] nums, ResRefType rt, int len, int target, int sum){
    if(sum > target){
      return;
    }
    if(sum == target){
      rt.cs++;
      return;
    }else{
      for(int i = 0; i < len; i++){
        combinationSum4DFS(nums, rt, len, target, sum + nums[i]);
      }
    }
  }
}

class ResRefType{
  int cs = 0;
}
