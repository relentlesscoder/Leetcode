package org.wshuai.leetcode;

/**
 * Created by Wei on 12/17/2019.
 * #0992 https://leetcode.com/problems/subarrays-with-k-different-integers/
 */
public class SubarraysWithKDifferentIntegers {

	// time O(n), space O(MAX)
	public int subarraysWithKDistinct(int[] nums, int k) {
		return solve(nums, k) - solve(nums, k + 1);
	}

	private int solve(int[] nums, int k) {
		int res = 0, n = nums.length;
		int[] freq = new int[20_001];
		for (int i = 0, j = 0, distinct = 0; i < n; i++) {
			if (freq[nums[i]]++ == 0) {
				distinct++;
			}
			while (distinct >= k) {
				if (--freq[nums[j++]] == 0) {
					distinct--;
				}
			}
			res += j;
		}
		return res;
	}
}
