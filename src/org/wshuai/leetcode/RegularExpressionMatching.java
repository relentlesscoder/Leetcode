package org.wshuai.leetcode;

/**
 * Created by Wei on 11/01/2016.
 * #0010 https://leetcode.com/problems/regular-expression-matching/
 */
public class RegularExpressionMatching {
	//DP, time O(m*n), space O(m*n)
	public boolean isMatch(String s, String p) {
		s = s == null ? "" : s;
		p = p == null ? "" : p;
		if(s.length() == 0 && p.length() == 0){
			return true;
		}
		if(p.length() == 0){
			return false;
		}
		while(p.indexOf("**") >= 0){
			p = p.replaceAll("\\*\\*", "*");
		}
		int r = p.length(), c = s.length();
		boolean[][] dp = new boolean[r + 1][c + 1];
		dp[0][0] = true;
		for(int i = 1; i <= r; i++){
			for(int j = 0; j <= c; j++){
				char pc = p.charAt(i - 1);
				if(j == 0){
					// to handle the special case:
					// p -> "a*", s -> ""
					if(pc == '*'){
						dp[i][j] = dp[i - 2][j];
					}
					continue;
				}
				char sc = s.charAt(j - 1);
				// match one single character
				if(pc == sc || pc == '.'){
					dp[i][j] = dp[i - 1][j - 1];
				}else if(pc == '*'){
					// If the previous character is '.' or mathes the character in
					// s, we need to consider the cases that are repeating zero,
					// one and more than 1 times.
					// Otherwise, we need to ignore the wildcard.
					dp[i][j] = dp[i - 2][j];
					if(p.charAt(i - 2) == sc || p.charAt(i - 2) == '.'){
						dp[i][j] = dp[i][j] || dp[i - 1][j] || dp[i][j - 1];
					}

					/* verbose version with boundary checking
					// repeating only once
					dp[i][j] = dp[i - 1][j];
					// repeating more than once
					if(i >= 2 && p.charAt(i - 2) == sc || p.charAt(i - 2) == '.'){
						dp[i][j] = dp[i][j] || dp[i][j - 1];
					}
					// repeating zero times
					if(i >= 3){
						dp[i][j] = dp[i][j] || dp[i - 2][j];
					}
					*/
				}
			}
		}
		return dp[r][c];
	}
}
