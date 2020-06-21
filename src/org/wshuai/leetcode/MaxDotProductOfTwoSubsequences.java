package org.wshuai.leetcode;

/**
 * Created by Wei on 05/24/2020.
 * #1458 https://leetcode.com/problems/max-dot-product-of-two-subsequences/
 */
public class MaxDotProductOfTwoSubsequences {

	// time O(m*n), space O(m*n)
	public int maxDotProduct(int[] nums1, int[] nums2) {
		int m = nums1.length, n = nums2.length;
		// dp[i][j] denotes the max dot product of sub-problem
		// nums1[0 .. i - 1] and nums2[0 .. j - 1]
		int[][] dp = new int[m + 1][n + 1];
		// handle edge cases
		for(int i = 0; i <= m; i++){
			dp[i][0] = Integer.MIN_VALUE;
		}
		for(int i = 0; i <= n; i++){
			dp[0][i] = Integer.MIN_VALUE;
		}
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= n; j++){
				// 1. use dot product of nums1[i - 1] and nums2[j - 1]
				// 2. only use nums1[i - 1]
				// 3. only use nums2[j - 1]
				dp[i][j] = Math.max(nums1[i - 1] * nums2[j - 1] + Math.max(dp[i - 1][j - 1], 0),
					Math.max(dp[i - 1][j], dp[i][j - 1]));
			}
		}
		return dp[m][n];
	}
}
