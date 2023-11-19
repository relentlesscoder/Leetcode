package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/26/2023.
 * #2461 https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/
 */
public class MaximumSumOfDistinctSubarraysWithLengthK {

	// time O(n), space O(1)
	public long maximumSubarraySum(int[] nums, int k) {
		long res = 0L, sum = 0L;
		int n = nums.length;
		int[] lastIndex = new int[100_001];
		Arrays.fill(lastIndex, -1);
		for (int start = 0, end = 0; end < n; end++) {
			while (lastIndex[nums[end]] != -1 || end - start + 1 > k) {
				sum -= nums[start];
				lastIndex[nums[start++]] = -1;
			}
			sum += nums[end];
			if (end - start + 1 == k) {
				res = Math.max(res, sum);
			}
			lastIndex[nums[end]] = end;
		}
		return res;
	}
}
