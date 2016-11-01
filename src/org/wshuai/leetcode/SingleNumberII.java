package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/16.
 * #137 https://leetcode.com/problems/single-number-ii/
 */
public class SingleNumberII {
  public int singleNumber(int[] nums) {
    int len = nums.length;
    int single = 0;
    for(int i = 0; i < 32; i++){
      int sum = 0;
      for(int j = 0; j < len; j++){
        sum += (nums[j] >> i) & 1;
      }

      single |= (sum % 3) << i;
    }

    return single;
  }
}
