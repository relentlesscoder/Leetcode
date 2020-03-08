package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/2016.
 * #0453 https://leetcode.com/problems/minimum-moves-to-equal-array-elements/
 */
public class MinimumMovesToEqualArrayElements {
	// time O(n)
	// https://leetcode.com/problems/minimum-moves-to-equal-array-elements/discuss/93817/It-is-a-math-question
	public int minMoves(int[] nums) {
		int n = nums.length, min = nums[0], sum = nums[0];
		for(int i = 1; i < n; i++){
			min = Math.min(min, nums[i]);
			sum += nums[i];
		}
		return sum - min * n;
	}
}
