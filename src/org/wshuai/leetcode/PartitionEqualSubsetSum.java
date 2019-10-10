package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/11/16.
 * #416 https://leetcode.com/problems/partition-equal-subset-sum/
 */
public class PartitionEqualSubsetSum {
	public boolean canPartition(int[] nums) {
		int sum = 0;
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			sum += nums[i];
		}
		if (sum % 2 == 1) {
			return false;
		}
		int target = sum / 2;
		boolean[] aux = new boolean[target + 1];
		aux[0] = true;
		for (int i = 0; i < len; i++) {
			for (int j = target; j >= nums[i]; j--) {
				aux[j] = aux[j] || aux[j - nums[i]];
			}
		}
		return aux[target];
	}

	// Knapsack without space optimization
	public boolean canPartitionDP(int[] nums) {
		int sum = 0;

		for (int num : nums) {
			sum += num;
		}

		if ((sum & 1) == 1) {
			return false;
		}
		sum /= 2;

		int n = nums.length;
		boolean[][] dp = new boolean[n+1][sum+1];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], false);
		}

		dp[0][0] = true;

		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < sum+1; j++) {
				if (j >= nums[i-1]) {
					dp[i][j] = (dp[i-1][j] || dp[i-1][j-nums[i-1]]);
				}else{
					dp[i][j] = dp[i-1][j];
				}
			}
		}

		return dp[n][sum];
	}
}
