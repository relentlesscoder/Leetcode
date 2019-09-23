package org.wshuai.leetcode;

/**
 * Created by Wei on 9/21/19.
 * #1130 https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
 */
public class MinimumCostTreeFromLeafValues {
	public int mctFromLeafValues(int[] arr) {
		int[][] dp = new int[arr.length][arr.length];
		int[][] max = new int[arr.length][arr.length];
		for (int i = 0; i < arr.length; i++) {
			int localMax = 0;
			for (int j = i; j < arr.length; j++) {
				if (arr[j] > localMax) {
					localMax = arr[j];
				}
				max[i][j] = localMax;
			}
		}
		for (int len = 1; len < arr.length; len++) {
			for (int left = 0; left + len < arr.length; left++) {
				int right = left + len;
				dp[left][right] = Integer.MAX_VALUE;
				if (len == 1) {
					dp[left][right] = arr[left] * arr[right];
				} else {
					for (int k = left; k < right; k++) {
						dp[left][right] = Math.min(dp[left][right], dp[left][k] + dp[k + 1][right] + max[left][k] * max[k + 1][right]);
					}
				}
			}
		}
		return dp[0][arr.length - 1];
	}
}
