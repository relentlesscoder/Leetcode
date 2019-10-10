package org.wshuai.algorithm.dynamicProgramming;

public class LongestPalindromicSubsequence {

	public int longestPalindromic(String S){
		int res = 0;
		int len = S.length();
		int[][] dp = new int[len][len];

		for(int l = 1; l <= len; l++){
			for(int i = 0; i < len - l + 1; i++){
				int j = i + l - 1;
				if(l == 1){
					dp[i][j] = 1;
				}else if(S.charAt(i) == S.charAt(j)){
					dp[i][j] = dp[i + 1][j - 1] + 2;
				}else{
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
				res = Math.max(res, dp[i][j]);
			}
		}
		return res;
	}

}
