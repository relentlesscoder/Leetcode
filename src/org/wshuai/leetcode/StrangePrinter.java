package org.wshuai.leetcode;

/**
 * Created by Wei on 11/28/2019.
 * #0664 https://leetcode.com/problems/strange-printer/
 */
public class StrangePrinter {

	// time O(n^2), space O(n^2)
	// https://www.youtube.com/watch?v=YQQUGsb7mww
	public int strangePrinter(String s) {
		if(s == null || s.isEmpty()){
			return 0;
		}
		s = preprocess(s.toCharArray());
		int n = s.length();
		int[][] dp = new int[n][n];
		return dfs(s.toCharArray(), 0, n - 1, dp);
	}

	private int dfs(char[] s, int i, int j, int[][] dp){
		if(i > j){
			return 0;
		}
		if(dp[i][j] > 0){
			return dp[i][j];
		}
		dp[i][j] = dfs(s, i, j - 1, dp) + 1;
		for(int k = i; k < j; k++){
			if(s[k] != s[j]){
				continue;
			}
			dp[i][j] = Math.min(dp[i][j], dfs(s, i, k, dp) + dfs(s, k + 1, j - 1, dp));
		}
		return dp[i][j];
	}

	private String preprocess(char[] arr){
		StringBuilder sb = new StringBuilder();
		sb.append(arr[0]);
		for(int i = 1; i < arr.length; i++){
			if(arr[i] != arr[i - 1]){
				sb.append(arr[i]);
			}
		}
		return sb.toString();
	}
}
