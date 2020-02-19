package org.wshuai.leetcode;

import java.util.Random;

/**
 * Created by Wei on 11/19/2016.
 * #0384 https://leetcode.com/problems/shuffle-an-array/
 */
public class ShuffleAnArray {
	private int[] nums;
	private Random rand;

	public ShuffleAnArray(int[] nums) {
		this.nums = nums;
		rand = new Random();
	}

	/** Resets the array to its original configuration and return it. */
	public int[] reset() {
		return nums;
	}

	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
		int[] copy = nums.clone();
		for(int i = 0; i < copy.length; i++){
			int j = rand.nextInt(i + 1);
			if(j == i){
				continue;
			}
			// probability of nums[i] in any position
			// in range 0 to i - 1:
			// (1 - 1 / (i + 1)) * 1 / i = 1 / (i + 1)
			int temp = copy[i];
			copy[i] = copy[j];
			copy[j] = temp;
		}
		return copy;
	}
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
