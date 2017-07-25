package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 7/25/2017.
 * #628 https://leetcode.com/problems/maximum-product-of-three-numbers/
 */
public class MaximumProductOfThreeNumbers {
  public int maximumProduct(int[] nums) {
    Arrays.sort(nums);
    return Math.max(nums[0]*nums[1]*nums[nums.length-1],
            nums[nums.length-1]*nums[nums.length-2]*nums[nums.length-3]);
  }
}
