package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 1/25/17.
 * #494 https://leetcode.com/problems/target-sum/
 */
public class TargetSum {
  public int findTargetSumWaysRecursive(int[] nums, int S) {
    RefType rt = new RefType();
    findTargetSumWaysUtil(nums, S, rt, 0, nums.length);
    return rt.val;
  }

  private void findTargetSumWaysUtil(int[] nums, int sum, RefType rt, int curr, int len){
    if(curr == len-1){
      if(sum == 0 && nums[curr] == 0){
        rt.val+=2;
      }else if(sum == nums[curr] || sum == nums[curr]*(-1)){
        rt.val++;
      }else{
        return;
      }
    }else{
      findTargetSumWaysUtil(nums, sum-nums[curr], rt, curr+1, len);
      findTargetSumWaysUtil(nums, sum+nums[curr], rt, curr+1, len);
    }
  }

  //DFS, TLE
  public int findTargetSumWaysDFS(int[] nums, int S) {
    List<Integer> curr = new ArrayList<Integer>();
    curr.add(0);
    return findTargetSumWaysDFS(nums, S, 0, curr, nums.length);
  }

  private int findTargetSumWaysDFS(int[] nums, int S, int index, List<Integer> curr, int len){
    int size = curr.size();
    if(index == len){
      int count = 0;
      for(int i = 0; i < size; i++){
        if(curr.get(i) == S){
          count++;
        }
      }
      return count;
    }else{
      int cv = nums[index];
      int neg = nums[index]*(-1);
      for(int i = 0; i < size; i++){
        int csum = curr.get(i);
        curr.set(i, csum+cv);
        curr.add(csum+neg);
      }
    }
    return findTargetSumWaysDFS(nums, S, index+1, curr, len);
  }
}
