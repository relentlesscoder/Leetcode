package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/16.
 * #453 https://leetcode.com/problems/minimum-moves-to-equal-array-elements/
 */
public class MinimumMovesToEqualArrayElements {
	//O(n)
	public int minMoves(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int min = nums[0];
		int len = nums.length;
		for (int i = 1; i < len; i++) {
			min = nums[i] < min ? nums[i] : min;
		}
		int moves = 0;
		//Increment n-1 elements by 1 equals decrement 1 element by 1
		for (int i = 0; i < len; i++) {
			moves += nums[i] - min;
		}
		return moves;
	}
}
