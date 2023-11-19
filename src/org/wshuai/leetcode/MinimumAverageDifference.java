package org.wshuai.leetcode;

/**
 * Created by Wei on 09/18/2023.
 * #2256 https://leetcode.com/problems/minimum-average-difference/
 */
public class MinimumAverageDifference {

	// time O(n), space O(1)
	public int minimumAverageDifference(int[] nums) {
		int res = 0, n = nums.length;
		long min = Long.MAX_VALUE, sum = 0L, curr = 0L;
		for (int num : nums) {
			sum += num;
		}
		for (int i = 0; i < n; i++) {
			curr += nums[i];
			sum -= nums[i];
			long avgLeft = curr / (i + 1), avgRight = (i == n - 1) ? 0 : sum / (n - i - 1);
			long avgDiff = Math.abs(avgLeft - avgRight);
			if (avgDiff < min) {
				min = avgDiff;
				res = i;
			}
		}
		return res;
	}
}
