package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 3/27/2017.
 * #376 https://leetcode.com/problems/wiggle-subsequence/
 */
public class WiggleSubsequence {
	//O(n)
	public int wiggleMaxLength(int[] nums) {
	  int len = nums.length;
		if(len < 2){
		  return len;
    }
		for (int i = 0; i < len - 1; i++) {
			nums[i] = nums[i + 1] - nums[i];
		}
		int max = 0;
		int prev = 0;
		for (int i = 0; i < len - 1; i++) {
			if (nums[i] != 0 && (prev == 0 || oppositeSign(nums[i], prev))) {
				max++;
			}
			if (nums[i] != 0) {
				prev = nums[i];
			}
		}
		return max == 0 ? 1 : max + 1;
	}

	private boolean oppositeSign(int x, int y) {
	  return (x > 0 && y < 0) || (x < 0 && y > 0);
	}

  public int wiggleMaxLengthDP(int[] nums) {
    if(nums.length < 2){
      return nums.length;
    }
    int[] up = new int[nums.length];
    int[] down = new int[nums.length];
    Arrays.fill(up, 1);
    Arrays.fill(down, 1);
    for(int i = 1; i < nums.length; i++){
      for(int j = i - 1; j >= 0; j--){
        if(nums[i] > nums[j]){
          up[i] = Math.max(down[j] + 1, up[i]);
        }else if(nums[i] < nums[j]){
          down[i] = Math.max(up[j] + 1, down[i]);
        }
      }
    }
    return Math.max(up[nums.length - 1], down[nums.length - 1]);
  }
}
