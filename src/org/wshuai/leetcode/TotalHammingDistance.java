package org.wshuai.leetcode;

/**
 * Created by Wei on 3/7/17.
 * #477 https://leetcode.com/problems/total-hamming-distance/
 */
public class TotalHammingDistance {
  public int totalHammingDistance(int[] nums) {
    int count = 0;
    int len = nums.length;
    //for each bit position, if the number of ones are k,
    // then the total of hamming distance at that bit position is k*(n-k)
    for(int i = 0; i < 32; i++){
      int ones = 0;
      for(int j = 0; j < len; j++){
        ones += (nums[j] >> i)&1;
      }
      count += ones*(len-ones);
    }
    return count;
  }
}
