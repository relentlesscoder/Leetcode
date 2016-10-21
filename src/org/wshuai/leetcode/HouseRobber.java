package org.wshuai.leetcode;

/**
 * Created by Wei on 10/17/16.
 * #198 https://leetcode.com/problems/house-robber/
 */
public class HouseRobber {
  public int rob(int[] nums) {
    if(nums == null || nums.length == 0){
      return 0;
    }
    int mx = nums[0];
    int len = nums.length;
    int[] max = new int[len + 1];
    max[0] = 0;
    max[1] = nums[0];
    for(int i = 2; i < len + 1; i++){
      int m1 = max[i - 1];
      int m2 = max[i - 2] + nums[i - 1];
      max[i] = m1 > m2 ? m1 : m2;
      mx = max[i] > mx ? max[i] : mx;
    }
    return mx;
  }
}
