package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2019.
 * #1143 https://leetcode.com/problems/longest-common-subsequence/
 */
public class LongestCommonSubsequence {

	// time O(m*n), space O(n)
	public int longestCommonSubsequence(String text1, String text2) {
		int m = text1.length(), n = text2.length();
		int[] dp = new int[n + 1];
		for(int i = 1; i <= m; i++){
			int prev = dp[0];
			for(int j = 1; j <= n; j++){
				int temp = dp[j];
				if(text1.charAt(i - 1) == text2.charAt(j - 1)){
					dp[j] = prev + 1;
				}else{
					dp[j] = Math.max(dp[j - 1], dp[j]);
				}
				prev = temp;
			}
		}
		return dp[n];
	}

	// time O(m*n), space O(m*n)
	public int longestCommonSubsequence2D(String text1, String text2) {
		int m = text1.length(), n = text2.length();
		int[][] dp = new int[m + 1][n + 1];
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= n; j++){
				if(text1.charAt(i - 1) == text2.charAt(j - 1)){
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}else{
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}
		return dp[m][n];
	}
}
