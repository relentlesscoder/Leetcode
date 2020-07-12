package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 07/12/2020.
 * #1509 https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/
 */
public class MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {

	// time O(n*log(n))
	public int minDifference(int[] nums) {
		int n = nums.length;
		if(n <= 4){
			return 0;
		}
		Arrays.sort(nums);
		int res = nums[n - 2] - nums[2];
		res = Math.min(nums[n - 3] - nums[1], res);
		res = Math.min(nums[n - 4] - nums[0], res);
		res = Math.min(nums[n - 3] - nums[1], res);
		res = Math.min(nums[n - 2] - nums[2], res);
		res = Math.min(nums[n - 1] - nums[3], res);
		return res;
	}
}
