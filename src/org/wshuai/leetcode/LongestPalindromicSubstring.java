package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0005 https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {
	// time O(n^2), space O(n^2)
	// https://leetcode.com/articles/longest-palindromic-substring/
	public String longestPalindromeDP(String s) {
		int n = s.length();
		int max = 0;
		int[] res = new int[]{0, 0};
		int[][] dp = new int[n][n];
		for(int l = 1; l <= n; l++){
			for(int i = 0; i + l - 1 < n; i++){
				int j = i + l - 1;
				if(i == j){
					dp[i][j] = 1;
				}else if(s.charAt(i) == s.charAt(j) && (i + 1 == j || dp[i + 1][j - 1] > 0)){
					dp[i][j] = 2 + (i + 1 == j ? 0 : dp[i + 1][j - 1]);
				}else{
					dp[i][j] = 0;
				}
				if(dp[i][j] > max){
					max = dp[i][j];
					res[0] = i;
					res[1] = j + 1;
				}
			}
		}
		return s.substring(res[0], res[1]);
	}
}
