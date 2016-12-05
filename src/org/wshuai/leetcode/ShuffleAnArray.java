package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Wei on 11/19/16.
 * #384 https://leetcode.com/problems/shuffle-an-array/
 */
public class ShuffleAnArray {
  private int[] nums;
  private int[] out;
  private Random random;

  public ShuffleAnArray(int[] nums) {
    this.nums = nums;
    this.out = Arrays.copyOf(nums, nums.length);
    this.random = new Random();
  }

  /** Resets the array to its original configuration and return it. */
  public int[] reset() {
    this.out = Arrays.copyOf(nums, nums.length);
    return out;
  }

  /** Returns a random shuffling of the array. */
  public int[] shuffle() {
    int len = nums.length;
    for(int i = 0; i < len; i++){
      int pos = random.nextInt(len-i)+i;
      int temp = out[pos];
      out[pos] = out[i];
      out[i] = temp;
    }
    return out;
  }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
