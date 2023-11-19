package org.wshuai.leetcode;

/**
 * Created by Wei on 09/18/2023.
 * #2219 https://leetcode.com/problems/maximum-sum-score-of-array/
 */
public class MaximumSumScoreOfArray {

	// time O(n), space O(1)
	public long maximumSumScore(int[] nums) {
		long res = Long.MIN_VALUE, sum = 0, curr = 0;
		for (int num : nums) {
			sum += num;
		}
		for (int i = 0; i < nums.length; i++) {
			curr += nums[i];
			long score = Math.max(curr, sum);
			res = Math.max(res, score);
			sum -= nums[i];
		}
		return res;
	}
}
