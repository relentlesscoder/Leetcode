package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 2/6/17.
 * #115 https://leetcode.com/problems/distinct-subsequences/
 */
public class DistinctSubsequences {
	//DP
	public int numDistinct(String s, String t) {
		int c = s.length();
		int r = t.length();
		int[][] dp = new int[r + 1][c + 1];
		Arrays.fill(dp[0], 1);
		for(int i = 1; i <= r; i++){
			for(int j = 1; j <= c; j++){
				if(t.charAt(i - 1) == s.charAt(j - 1)){
					dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
				}else{
					dp[i][j] = dp[i][j - 1];
				}
			}
		}
		return dp[r][c];
	}
}
