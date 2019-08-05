package org.wshuai.leetcode;

/**
 * Created by Wei on 8/30/17.
 * #330 https://leetcode.com/problems/patching-array/
 */
public class PatchingArray {
  // See https://discuss.leetcode.com/topic/35494/solution-explanation
  public int minPatches(int[] nums, int n) {
    long miss = 1;
    int added = 0, i = 0;
    while(miss <= n){
      if(i < nums.length && nums[i] <= miss){
        miss += nums[i++];
      }else{
        miss += miss;
        added++;
      }
    }
    return added;
  }
}
