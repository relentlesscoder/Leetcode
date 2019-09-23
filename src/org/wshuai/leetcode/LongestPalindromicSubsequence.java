package org.wshuai.leetcode;

/**
 * Created by Wei on 9/20/19.
 * #516 https://leetcode.com/problems/longest-palindromic-subsequence/
 */
public class LongestPalindromicSubsequence {
	// see https://www.youtube.com/watch?v=OZX1nqaQ_9M
	public int longestPalindromeSubseq(String s) {
		int len = s.length();
		int[][] dp = new int[len][len];
		for (int l = 1; l <= len; l++) {
			for (int i = 0; i <= len - l; i++) {
				int j = i + l - 1;
				if (i == j) {
					dp[i][j] = 1;
					continue;
				}
				if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = dp[i + 1][j - 1] + 2;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[0][len - 1];
	}
}
