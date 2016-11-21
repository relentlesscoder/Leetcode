package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 11/20/16.
 * #421 https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
 */
public class MaximumXOROfTwoNumbersInAnArray {
  //O(n), see http://massivealgorithms.blogspot.com/2016/10/leetcode-421-maximum-xor-of-two-numbers.html
  public int findMaximumXOR(int[] nums) {
    if(nums == null || nums.length == 0){
      return 0;
    }
    int max = 0;
    int mask = 0;
    for(int i = 31; i >= 0; i--){
      mask = mask | (1 << i);
      Set<Integer> set = new HashSet<Integer>();
      for(int num: nums){
        set.add(num & mask);
      }
      int temp = max | (1 << i);
      for(int val: set){
        if(set.contains(temp ^ val)){
          max = temp;
          break;
        }
      }
    }
    return max;
  }
}
