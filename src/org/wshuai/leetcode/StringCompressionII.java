package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/15/2020.
 * #1531 https://leetcode.com/problems/string-compression-ii/
 */
public class StringCompressionII {

	// time O(k*n^2), space O(k*n)
	public int getLengthOfOptimalCompression(String s, int k) {
		int n = s.length();
		int[][] dp = new int[n + 1][k + 1];
		for(int i = 1; i <= n; i++){
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		for(int i = 1; i <= n; i++){
			for(int j = 0; j <= k; j++){
				if(j > 0){
					dp[i][j] = dp[i - 1][j - 1]; // remove s[i - 1]
				}
				// keep s[n - 1]
				int removed = 0, count = 0;
				for(int p = i; p > 0; p--){
					if(s.charAt(p - 1) == s.charAt(i - 1)){ // if s[p - 1] is same as s[i - 1]
						count++;
					}else if(++removed > j){ // otherwise remove it to keep the current chain
						break;
					}
					dp[i][j] = Math.min(dp[i][j], dp[p - 1][j - removed]
						+ encodedLength(count));
				}
			}
		}
		return dp[n][k];
	}

	// time O(26*k*n^2), space O(26*k*n^2)
	// https://leetcode.com/problems/string-compression-ii/discuss/757506/Detailed-Explanation-Two-ways-of-DP-from-33-to-100
	public int getLengthOfOptimalCompression4D(String s, int k) {
		int n = s.length();
		int[][][][] dp = new int[n][26][n + 1][k + 1];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < 26; j++){
				for(int x = 0; x <= n; x++){
					Arrays.fill(dp[i][j][x], Integer.MAX_VALUE);
				}
			}
		}
		return dfs(0, 0, 0, k, s, dp);
	}

	private int dfs(int i, int c, int l, int k, String s, int[][][][] dp){
		if(i == s.length()){
			return encodedLength(l);
		}
		if(dp[i][c][l][k] == Integer.MAX_VALUE){
			int val = s.charAt(i) - 'a';
			if(k > 0){
				dp[i][c][l][k] = Math.min(dp[i][c][l][k], dfs(i + 1, c, l, k - 1, s, dp));
			}
			if(c == val){
				dp[i][c][l][k] = Math.min(dp[i][c][l][k], dfs(i + 1, c, l + 1, k, s, dp));
			}else{
				dp[i][c][l][k] = Math.min(dp[i][c][l][k], encodedLength(l) + dfs(i + 1, val, 1, k, s, dp));
			}
		}
		return dp[i][c][l][k];
	}

	private int encodedLength(int l){
		if(l <= 1){
			return l;
		}
		if(l < 10){
			return 2;
		}
		if(l < 100){
			return 3;
		}
		return 4;
	}
}
