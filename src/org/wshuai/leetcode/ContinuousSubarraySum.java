package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 3/5/17.
 * #523 https://leetcode.com/problems/continuous-subarray-sum/
 */
public class ContinuousSubarraySum {
  public boolean checkSubarraySum(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    map.put(0, -1);
    int len = nums.length;
    int sum = 0;
    for(int i = 0; i < len; i++){
      sum+=nums[i];
      if(k != 0){
        sum %= k;
      }
      int idx = map.getOrDefault(sum, -100);
      if(idx != -100){
        if(i-idx > 1){
          return true;
        }
      }else{
        map.put(sum, i);
      }
    }
    return false;
  }
}
