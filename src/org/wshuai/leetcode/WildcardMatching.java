package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/30/2016.
 * #0044 https://leetcode.com/problems/wildcard-matching/
 */
public class WildcardMatching {

	// time O(m * n), space O(n)
	public boolean isMatch(String s, String p) {
		int c = s.length();
		int r = p.length();
		boolean[][] dp = new boolean[2][c + 1];
		dp[0][0] = true;
		int prev = 0, cur = 1;
		for(int i = 1; i <= r; i++){
			for(int j = 0; j <= c; j++){
				dp[cur][j] = false;
				char pc = p.charAt(i - 1);
				if(j == 0){
					if(pc == '*' && dp[prev][j]){
						dp[cur][j] = true;
					}
					continue;
				}
				char sc = s.charAt(j - 1);
				if(sc == pc || pc == '?'){
					dp[cur][j] = dp[prev][j - 1];
				}else if(pc == '*'){
					dp[cur][j] = dp[prev][j] || dp[cur][j - 1];
				}
			}
			prev = cur;
			cur = 1 - prev;
		}
		return dp[prev][c];
	}

	// time O(m * n), space O(m * n)
	public boolean isMatch2DDP(String s, String p) {
		int c = s.length();
		int r = p.length();
		boolean[][] dp = new boolean[r + 1][c + 1];
		Arrays.fill(dp[0], false);
		dp[0][0] = true;
		for(int i = 1; i <= r; i++){
			for(int j = 0; j <= c; j++){
				char pc = p.charAt(i - 1);
				if(j == 0){
					if(pc == '*' && dp[i - 1][j]){
						dp[i][j] = true;
					}
					continue;
				}
				char sc = s.charAt(j - 1);
				if(sc == pc || pc == '?'){
					dp[i][j] = dp[i - 1][j - 1];
				}else if(pc == '*'){
					dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
				}
			}
		}
		return dp[r][c];
	}
}
