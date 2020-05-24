package org.wshuai.leetcode;

/**
 * Created by Wei on 05/24/2020.
 * #1458 https://leetcode.com/problems/max-dot-product-of-two-subsequences/
 */
public class MaxDotProductOfTwoSubsequences {

	// time O(m*n), space O(m*n)
	public int maxDotProduct(int[] nums1, int[] nums2) {
		int m = nums1.length, n = nums2.length;
		// denotes max dot product ends at nums1[i]
		// and nums2[j] (i or j may not be used)
		int[][] dp = new int[m][n];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				// base case, length of subsequence is 1
				dp[i][j] = nums1[i] * nums2[j];
				// use the product of nums1[i] and nums2[j]
				if(i > 0 && j > 0){
					dp[i][j] += Math.max(0, dp[i - 1][j - 1]);
				}
				// skip nums1[i]
				if(i > 0){
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
				}
				// skip nums2[j]
				if(j > 0){
					dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
				}
			}
		}
		return dp[m - 1][n - 1];
	}
}
