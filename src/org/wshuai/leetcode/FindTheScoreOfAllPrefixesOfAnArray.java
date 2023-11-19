package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2023.
 * #2640 https://leetcode.com/problems/find-the-score-of-all-prefixes-of-an-array/
 */
public class FindTheScoreOfAllPrefixesOfAnArray {

	// time O(n), space O(n)
	public long[] findPrefixScore(int[] nums) {
		int n = nums.length, max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, nums[i]);
			nums[i] += max;
		}
		long[] res = new long[n];
		res[0] = nums[0];
		long prefixSum = nums[0];
		for (int i = 1; i < n; i++) {
			res[i] = 1L * nums[i] + prefixSum;
			prefixSum += nums[i];
		}
		return res;
	}
}
