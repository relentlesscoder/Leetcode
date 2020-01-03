package org.wshuai.leetcode;

/**
 * Created by Wei on 11/28/19.
 * #689 https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
 */
public class MaximumSumOf3NonOverlappingSubarrays {
	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
		int[] res = new int[3];
		if (nums == null || k <= 0 || nums.length < 3 * k) {
			return res;
		}

		//calculate prefix sum
		//prefix[j] - prefix[i] = sum of {i ... j - 1}
		int n = nums.length;
		int[] sums = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			sums[i] = sums[i - 1] + nums[i - 1];
		}

		int[][] dp = new int[4][n + 1];
		int[][] pos = new int[4][n + 1];

		//dp[i][j] denotes max sum of i non-overlapping
		//subarray within range [k * i, j - 1]
		for (int i = 1; i <= 3; i++) {
			for (int j = k * i; j <= n; j++) {
				//sums[j] - sums[j - k] = sum of {j - k, j - k + 1 ... j - 1}
				//dp[i - 1][j - k] = max sum (of i - 1) in range {k * i ... j - k - 1}
				int curSum = sums[j] - sums[j - k] + dp[i - 1][j - k];
				if (curSum > dp[i][j - 1]) {
					dp[i][j] = curSum;
					pos[i][j] = j - k;
				} else {
					dp[i][j] = dp[i][j - 1];
					pos[i][j] = pos[i][j - 1];
				}
			}
		}
		int index = n;
		for (int i = 2; i >= 0; i--) {
			res[i] = pos[i + 1][index];
			index = res[i];
		}
		return res;
	}
}
