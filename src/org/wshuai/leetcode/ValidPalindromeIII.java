package org.wshuai.leetcode;

/**
 * Created by Wei on 12/9/2019.
 * #1216 https://leetcode.com/problems/valid-palindrome-iii/
 */
public class ValidPalindromeIII {

	// time O(n^2), space O(n^2)
	// https://www.techiedelight.com/longest-palindromic-subsequence-using-dynamic-programming/
	public boolean isValidPalindrome(String s, int k) {
		int n = s.length();
		String t = new StringBuilder(s).reverse().toString(); // longest common subsequence of s and t
		int[][] dp = new int[n + 1][n + 1];
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= n; j++){
				if(s.charAt(i - 1) == t.charAt(j - 1)){
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}else{
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}
		return (n - dp[n][n] <= k);
	}
}
