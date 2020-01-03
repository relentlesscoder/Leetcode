package org.wshuai.leetcode;

/**
 * Created by Wei on 11/28/19.
 * #664 https://leetcode.com/problems/strange-printer/
 */
public class StrangePrinter {
	private int[][] dp;

	// https://www.youtube.com/watch?v=YQQUGsb7mww
	public int strangePrinter(String s) {
		if(s == null || s.length() == 0){
			return 0;
		}
		s = preProcess(s.toCharArray());
		int N = s.length();
		dp = new int[N][N];
		return dfs(s.toCharArray(), 0, N - 1);
	}

	private int dfs(char[] arr, int i, int j){
		if(i > j){
			return 0;
		}
		if(dp[i][j] > 0){
			return dp[i][j];
		}
		dp[i][j] = dfs(arr, i, j - 1) + 1;
		for(int k = i; k < j; k++){
			if(arr[k] != arr[j]){
				continue;
			}
			dp[i][j]  = Math.min(dp[i][j], dfs(arr, i, k) + dfs(arr, k + 1, j - 1));
		}
		return dp[i][j];
	}

	private String preProcess(char[] arr){
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
