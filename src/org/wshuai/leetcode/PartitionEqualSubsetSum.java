package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/16.
 * #416 https://leetcode.com/problems/partition-equal-subset-sum/
 */
public class PartitionEqualSubsetSum {
  public boolean canPartition(int[] nums) {
    int sum = 0;
    int len = nums.length;
    for(int i = 0; i < len; i++){
      sum += nums[i];
    }
    if(sum%2 == 1){
      return false;
    }
    int target = sum/2;
    boolean[] aux = new boolean[target+1];
    aux[0] = true;
    for(int i = 0; i < len; i++){
      for(int j = target; j >= nums[i]; j--){
        aux[j] = aux[j] || aux[j-nums[i]];
      }
    }
    return aux[target];
  }
}
