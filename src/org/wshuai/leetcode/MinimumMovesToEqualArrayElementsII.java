package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/23/2016.
 * #0462 https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/
 */
public class MinimumMovesToEqualArrayElementsII {
	// time O(n*log(n))
	public int minMoves2(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		Arrays.sort(nums);
		int res = 0, n = nums.length, target = nums[n / 2];
		for(int num : nums){
			res += Math.abs(target - num);
		}
		return res;
	}
}
