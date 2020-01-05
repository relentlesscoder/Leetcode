package org.wshuai.leetcode;

/**
 * Created by Wei on 01/05/2020.
 * #1312 https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
 */
public class MinimumInsertionStepsToMakeAStringPalindrome {
	public int minInsertions(String s) {
		String rev = new StringBuilder(s).reverse().toString();
		return s.length() - lcs(s, rev);
	}

	private int lcs(String s, String t){
		int n = s.length();
		int[][] dp = new int[n + 1][n + 1];
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= n; j++){
				if(s.charAt(i - 1) == t.charAt(j - 1)){
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}else{
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[n][n];
	}
}
