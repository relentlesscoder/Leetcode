package org.wshuai.leetcode;

/**
 * Created by Wei on 11/01/2016.
 * #0010 https://leetcode.com/problems/regular-expression-matching/
 */
public class RegularExpressionMatching {

	// time O(m*n), space O(m*n)
	public boolean isMatch(String s, String p) {
		if(p.length() == 0){
			return s.length() == 0;
		}
		int m = s.length(), n = p.length();
		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[0][0] = true;
		for(int j = 1; j <= n; j++){
			if(p.charAt(j - 1) == '*'){
				dp[0][j] = dp[0][j - 2]; // matches zero character
			}
		}
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= n; j++){
				if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.'){
					dp[i][j] = dp[i - 1][j - 1]; // '.' matches single character
				}else if(p.charAt(j - 1) == '*'){ // '*'
					dp[i][j] = dp[i][j - 2]; // matches zero character
					if(p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.'){
						dp[i][j] = dp[i][j] || dp[i - 1][j] || dp[i][j - 1];
						// dp[i - 1][j] matches more than one characters
						// dp[i][j - 1] matches one character
					}
				}
			}
		}
		return dp[m][n];
	}
}
