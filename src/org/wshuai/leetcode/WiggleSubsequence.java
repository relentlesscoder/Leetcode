package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 03/27/2017.
 * #0376 https://leetcode.com/problems/wiggle-subsequence/
 */
public class WiggleSubsequence {
	// time O(n)
	public int wiggleMaxLength(int[] nums) {
		int n = nums.length;
		if (n < 2) {
			return n;
		}
		for (int i = 0; i < n - 1; i++) {
			nums[i] = nums[i + 1] - nums[i];
		}
		int max = 0, prev = 0;
		for (int i = 0; i < n - 1; i++) {
			if (nums[i] != 0 && (prev == 0 || (nums[i] ^ prev) < 0)) {
				max++;
			}
			if (nums[i] != 0) {
				prev = nums[i];
			}
		}
		return max == 0 ? 1 : max + 1;
	}

	// time O(n^2)
	public int wiggleMaxLengthDP(int[] nums) {
		int n = nums.length;
		if (n < 2) {
			return n;
		}
		int[] up = new int[n];
		int[] down = new int[n];
		Arrays.fill(up, 1);
		Arrays.fill(down, 1);
		for (int i = 1; i < n; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (nums[i] > nums[j]) {
					up[i] = Math.max(down[j] + 1, up[i]);
				} else if (nums[i] < nums[j]) {
					down[i] = Math.max(up[j] + 1, down[i]);
				}
			}
		}
		return Math.max(up[n - 1], down[n - 1]);
	}
}
