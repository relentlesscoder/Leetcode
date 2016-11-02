package org.wshuai.leetcode;

/**
 * Created by Wei on 11/2/2016.
 * #260 https://leetcode.com/problems/single-number-iii/
 */
public class SingleNumberIII {
  public int[] singleNumber(int[] nums) {
    int[] res = new int[2];
    int xor = 0;
    int len = nums.length;
    for(int i = 0; i < len; i++){
      xor ^= nums[i];
    }

    //Find the first 1 bit, set all other bits to 0
    //Use two examples to help understanding: 01010101 -> 00000001, 10111000 -> 00001000
    int flag = xor&(~(xor-1));
    //Divide the array into two groups by flag bit
    for(int i = 0; i < len; i++){
      if((nums[i]&flag) == 0){
        res[0]^=nums[i];
      }else{
        res[1]^=nums[i];
      }
    }

    return res;
  }
}
