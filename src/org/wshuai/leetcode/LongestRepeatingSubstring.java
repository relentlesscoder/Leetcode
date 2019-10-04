package org.wshuai.leetcode;

/**
 * Created by Wei on 10/4/19.
 * #1062 https://leetcode.com/problems/longest-repeating-substring/
 */
public class LongestRepeatingSubstring {

	// 9 ms
	public int longestRepeatingSubstringSpaceOptimization(String S) {
		int max = 0;
		int N = S.length();
		int[] dp = new int[N + 1];
		for(int i = 1; i <= N; i++){
			int prev = dp[0];
			for(int j = i; j <= N; j++){
				int temp = dp[j];
				if(S.charAt(i - 1) == S.charAt(j - 1) && i != j){
					dp[j] = prev + 1;
					max = Math.max(dp[j], max);
				}else{
					dp[j] = 0;
				}
				prev = temp;
			}
		}
		return max;
	}

	// 15 ms
	public int longestRepeatingSubstring(String S) {
		int max = 0;
		int N = S.length();
		int[][] dp = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++){
			for(int j = i; j <= N; j++){
				if(S.charAt(i - 1) == S.charAt(j - 1) && i != j){
					dp[i][j] = dp[i - 1][j - 1] + 1;
					max = Math.max(dp[i][j], max);
				}else{
					dp[i][j] = 0;
				}
			}
		}
		return max;
	}
}
