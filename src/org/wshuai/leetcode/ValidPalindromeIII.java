package org.wshuai.leetcode;

/**
 * Created by Wei on 12/09/2019.
 * #1216 https://leetcode.com/problems/valid-palindrome-iii/
 */
public class ValidPalindromeIII {

	// time O(n^2), space O(n)
	public boolean isValidPalindromeOptimizedSpace(String s, int k) {
		int n = s.length();
		int[] dp = new int[n];
		for (int i = n - 2; i >= 0; i--) {
			int[] next = new int[n];
			for (int j = i + 1; j < n; j++) {
				if (s.charAt(i) == s.charAt(j)) {
					next[j] = dp[j - 1];
				} else {
					next[j] = 1 + Math.min(dp[j], next[j - 1]);
				}
			}
			dp = next;
		}
		return dp[n - 1] <= k;
	}

	// time O(n^2), space O(n^2)
	public boolean isValidPalindrome(String s, int k) {
		int n = s.length();
		int[][] dp = new int[n][n];
		for (int i = n - 2; i >= 0; i--) {
			for (int j = i + 1; j < n; j++) {
				if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = dp[i + 1][j - 1];
				} else {
					dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[0][n - 1] <= k;
	}

	// time O(n^2), space O(n^2)
	public boolean isValidPalindromeLPS(String s, int k) {
		int n = s.length();
		String t = new StringBuilder(s).reverse().toString(); // longest common subsequence of s and t
		int[][] dp = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}
		return (n - dp[n][n] <= k);
	}
}
