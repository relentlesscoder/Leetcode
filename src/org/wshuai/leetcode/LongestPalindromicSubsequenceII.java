package org.wshuai.leetcode;

/**
 * Created by Wei on 01/17/2021.
 * #1682 https://leetcode.com/problems/longest-palindromic-subsequence-ii/
 */
public class LongestPalindromicSubsequenceII {

	// time O(n^2), space O(n^2)
	public int longestPalindromeSubseq(String s) {
		int res = 0, n = s.length();
		int[][][] dp = new int[n][n][26];
		for(int l = 2; l <= n; l++){
			for(int i = 0; i + l - 1 < n; i++){
				int j = i + l - 1;
				for(int k = 0; k < 26; k++){
					dp[i][j][k] = Math.max(dp[i + 1][j][k], dp[i][j - 1][k]);
				}
				if(s.charAt(i) == s.charAt(j)){
					int max = 2;
					for(int k = 0; k < 26; k++){
						if(s.charAt(i) - 'a' == k){
							continue;
						}
						max = Math.max(max, 2 + dp[i + 1][j - 1][k]);
					}
					dp[i][j][s.charAt(i) - 'a']
						= Math.max(max, dp[i][j][s.charAt(i) - 'a']);
					res = Math.max(res, max);
				}
			}
		}
		return res;
	}
}
