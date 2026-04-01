package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 11/01/2019.
 * #0813 https://leetcode.com/problems/largest-sum-of-averages/
 */
public class LargestSumOfAverages {

	public double largestSumOfAveragesDPWithArray(int[] nums, int k) {
		int n = nums.length;
		double[] dp = new double[n + 1];
		Arrays.fill(dp, -(double) 1e6);
		dp[0] = 0.0;
		for (int i = 0; i < k; i++) {
			for (int j = n - 1; j >= i; j--) {
				double res = 0.0, sum = 0.0;
				for (int l = j, cnt = 0; l >= 0; l--) {
					sum += nums[l];
					cnt++;
					res = Math.max(res, sum / cnt + dp[l]);
				}
				dp[j + 1] = res;
			}
		}
		return dp[n];
	}

	public double largestSumOfAveragesDPWithGrid(int[] nums, int k) {
		int n = nums.length;
		double[][] dp = new double[k + 1][n + 1];
		Arrays.fill(dp[0], -(double) 1e6);
		dp[0][0] = 0.0;
		for (int i = 0; i < k; i++) {
			for (int j = n - 1; j >= i; j--) {
				double res = 0.0, sum = 0.0;
				for (int l = j, cnt = 0; l >= 0; l--) {
					sum += nums[l];
					cnt++;
					res = Math.max(res, sum / cnt + dp[i][l]);
				}
				dp[i + 1][j + 1] = res;
			}
		}
		return dp[k][n];
	}

	public double largestSumOfAveragesDFSWithMemorization(int[] nums, int k) {
		int n = nums.length;
		double[][] memo = new double[k][n];
		for (double[] row : memo) {
			Arrays.fill(row, -1.0);
		}
		return dfs(k - 1, n - 1, nums, memo);
	}

	private double dfs(int i, int j, int[] nums, double[][] memo) {
		if (j == -1) {
			return 0.0;
		}
		if (i == -1) {
			return -(double) 1e6;
		}
		if (memo[i][j] >= 0) {
			return memo[i][j];
		}
		double res = 0.0, sum = 0.0;
		for (int k = j, cnt = 0; k >= 0; k--) {
			sum += nums[k];
			cnt++;
			res = Math.max(res, sum / cnt + dfs(i - 1, k - 1, nums, memo));
		}
		return memo[i][j] = res;
	}
}
