package org.wshuai.leetcode;

import java.util.Random;

/**
 * Created by Wei on 11/18/2016.
 * #0398 https://leetcode.com/problems/random-pick-index/
 */
public class RandomPickIndex {
	private int[] nums;
	private Random rand;

	public RandomPickIndex(int[] nums) {
		this.nums = nums;
		rand = new Random();
	}

	public int pick(int target) {
		int res = -1, count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != target) {
				continue;
			}
			count++;
			if (rand.nextInt(count) == 0) {
				res = i;
			}
		}
		return res;
	}
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
