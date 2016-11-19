package org.wshuai.leetcode;

import java.util.Random;

/**
 * Created by Wei on 11/18/16.
 * #398 https://leetcode.com/problems/random-pick-index/
 */
public class RandomPickIndex {
  private int[] nums;
  private Random random;

  public RandomPickIndex(int[] nums) {
    this.nums = nums;
    this.random = new Random();
  }

  public int pick(int target) {
    int idx = 0;
    int cnt = 0;
    int len = nums.length;
    for(int i = 0; i < len; i++){
      if(nums[i] != target){
        continue;
      }
      cnt++;
      if(random.nextInt(cnt) == 0){
        idx = i;
      }
    }
    return idx;
  }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
