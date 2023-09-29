package org.wshuai.leetcode;

/**
 * Created by Wei on 09/29/2023.
 * #2574 https://leetcode.com/problems/left-and-right-sum-differences/
 */
public class LeftAndRightSumDifferences {

	// time O(n), space O(n)
	public int[] leftRightDifference(int[] nums) {
		int n = nums.length, prefixSum = 0, postfixSum = 0;
		int[] res = new int[n];
		for (int i = 0; i < n; i++) {
			res[i] = prefixSum;
			prefixSum += nums[i];
		}
		for (int i = n - 1; i >= 0; i--) {
			res[i] = Math.abs(res[i] - postfixSum);
			postfixSum += nums[i];
		}
		return res;
	}
}
