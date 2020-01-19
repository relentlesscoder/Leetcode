package org.wshuai.leetcode;

/**
 * Created by Wei on 02/05/2017.
 * #0087 https://leetcode.com/problems/scramble-string/
 */
public class ScrambleString {

	// time O(n^4), space O(n^3)
	public boolean isScramble(String s1, String s2) {
		int n = s1.length();
		if(n != s2.length()){
			return false;
		}
		if(s1.equals(s2)){
			return true;
		}
		boolean[][][] dp = new boolean[n + 1][n][n];
		for(int l = 1; l <= n; l++){
			for(int i = 0; i + l - 1 < n; i++){
				for(int j = 0; j + l - 1 < n; j++){
					// base case
					if(l == 1){
						dp[l][i][j] = s1.charAt(i) == s2.charAt(j);
						continue;
					}
					// if two string are scramble strings,
					// there are two cases:
					// 1. great and rgtea
					// 2. tearg and great
					for(int k = 1; k < l; k++){
						if((dp[k][i][j] && dp[l - k][i + k][j + k])
							|| (dp[k][i][j + l - k] && dp[l - k][i + k][j])){
							dp[l][i][j] = true;
							break;
						}
					}
				}
			}
		}
		return dp[n][0][0];
	}
}
