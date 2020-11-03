package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2019.
 * #0516 https://leetcode.com/problems/longest-palindromic-subsequence/
 */
public class LongestPalindromicSubsequence {

	// time O(n^2), space O(n)
	public int longestPalindromeSubseq(String s) {
		if(s == null || s.length() == 0){
			return 0;
		}
		int n = s.length();
		int[] dp = new int[n];
		for(int i = n - 1; i >= 0; i--){
			dp[i] = 1;
			// stores value dp[i + 1][j - 1]
			int downLeft = 0;
			for(int j = i + 1; j < n; j++){
				int temp = dp[j];
				if(s.charAt(i) == s.charAt(j)){
					dp[j] = downLeft + 2;
				}else{
					// dp[j] is dp[i + 1][j], dp[j - 1] is dp[i][j - 1]
					dp[j] = Math.max(dp[j], dp[j - 1]);
				}
				downLeft = temp;
			}
		}
		return dp[n - 1];
	}

	// time O(n^2), space O(n^2)
	public int longestPalindromeSubseq2DBottomUp(String s) {
		if(s == null || s.length() == 0){
			return 0;
		}
		int n = s.length();
		int[][] dp = new int[n][n];
		for(int i = n - 1; i >= 0; i--){
			for(int j = i; j < n; j++){
				if(i == j){
					dp[i][j] = 1;
					continue;
				}
				if(s.charAt(i) == s.charAt(j)){
					dp[i][j] = dp[i + 1][j - 1] + 2;
				}else{
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[0][n - 1];
	}

	// time O(n^2), space O(n^2)
	// https://www.youtube.com/watch?v=OZX1nqaQ_9M
	public int longestPalindromeSubseq2D(String s) {
		if(s == null || s.length() == 0){
			return 0;
		}
		int n = s.length();
		int[][] dp = new int[n][n];
		for(int l = 1; l <= n; l++){
			for(int i = 0; i + l <= n; i++){
				int j = i + l - 1;
				if(i == j){
					dp[i][j] = 1;
					continue;
				}
				if(s.charAt(i) == s.charAt(j)){
					dp[i][j] = dp[i + 1][j - 1] + 2;
				}else{
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[0][n - 1];
	}
}
