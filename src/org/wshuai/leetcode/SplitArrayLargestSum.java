package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/09/2019.
 * #0410 https://leetcode.com/problems/split-array-largest-sum/
 */
public class SplitArrayLargestSum {

	// time O(log(sum)), space O(1)
	public int splitArray(int[] nums, int k) {
		int low = 0, high = 0;
		for (int num : nums) {
			low = Math.max(low, num);
			high += num;
		}
		while (low < high) {
			int mid = (low + high) >> 1;
			if (canSplit(nums, k, mid)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	private boolean canSplit(int[] nums, int k, int threshold) {
		int count = 0, sum = 0;
		for (int num : nums) {
			if (sum + num > threshold) {
				sum = 0;
				if (++count >= k) {
					return false;
				}
			}
			sum += num;
		}
		return true;
	}

	// time O(n^2 * k), space O(n * k)
	public int splitArrayDP(int[] nums, int k) {
		int n = nums.length;
		int[][] dp = new int[n + 1][k + 1];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		dp[0][0] = 0;
		for (int i = 1; i <= n; i++) {
			int sum = 0;
			for (int j = i; j > 0; j--) {
				sum += nums[j - 1];
				for (int x = 1; x <= k; x++) {
					dp[i][x] = Math.min(dp[i][x], Math.max(sum, dp[j - 1][x - 1]));
				}
			}
		}
		return dp[n][k];
	}
}
