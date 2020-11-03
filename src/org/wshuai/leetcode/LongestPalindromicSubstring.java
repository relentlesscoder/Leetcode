package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0005 https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {

	private int longest, start, end;

	// time O(n^2), space O(1)
	public String longestPalindrome(String s) {
		longest = 0;
		start = end = -1;
		for(int i = 0; i < s.length(); i++){
			extend(s, i, i);
			extend(s, i, i + 1);
		}
		return s.substring(start, end + 1);
	}

	private void extend(String s, int l, int r){
		while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
			l--;
			r++;
		}
		l++;
		r--;
		if(r - l + 1 > longest){
			longest = r - l + 1;
			start = l;
			end = r;
		}
	}

	// time O(n^2), space O(n^2)
	// https://leetcode.com/articles/longest-palindromic-substring/
	public String longestPalindromeDP(String s) {
		int max = 0, n = s.length(), start = -1, end = -1;
		int[][] dp = new int[n][n];
		for(int l = 1; l <= n; l++){
			for(int i = 0; i + l - 1 < n; i++){
				int j = i + l - 1;
				if(i == j){
					dp[i][j] = 1;
				}else if(s.charAt(i) == s.charAt(j) && (i + 1 == j || dp[i + 1][j - 1] > 0)){
					dp[i][j] = dp[i + 1][j - 1] + 2;
				}
				if(dp[i][j] > max){
					max = dp[i][j];
					start = i;
					end = j + 1;
				}
			}
		}
		return s.substring(start, end);
	}
}
