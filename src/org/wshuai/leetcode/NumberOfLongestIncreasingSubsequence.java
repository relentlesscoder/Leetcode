package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/04/2019.
 * #0673 https://leetcode.com/problems/number-of-longest-increasing-subsequence/
 */
public class NumberOfLongestIncreasingSubsequence {
	// time O(n^2), space O(n)
	public int findNumberOfLIS(int[] nums) {
		int res = 0, n = nums.length, max = 1;
		if (n <= 1) {
			return n;
		}
		int[] length = new int[n], count = new int[n];
		Arrays.fill(length, 1);
		Arrays.fill(count, 1);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] <= nums[j]) {
					continue;
				}
				// longer subsequence can be formed by adding i to those end of j
				if (length[j] >= length[i]) {
					length[i] = length[j] + 1;
					count[i] = count[j];
				// same length (as current max length) can be formed by adding i to those end of j
				} else if (length[j] + 1 == length[i]) {
					count[i] += count[j];
				}
			}
			max = Math.max(max, length[i]);
		}
		for (int i = 0; i < n; i++) {
			if (length[i] == max) {
				res += count[i];
			}
		}
		return res;
	}
}
