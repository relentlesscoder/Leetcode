package org.wshuai.leetcode;

/**
 * Created by Wei on 09/02/2016.
 * #0072 https://leetcode.com/problems/edit-distance/
 */
public class EditDistance {
	// time O(m*n), space O(m*n) can be improve to O(m)
	public int minDistance(String word1, String word2) {
		int n = word1.length();
		int m = word2.length();
		if(m == 0 && n == 0){
			return 0;
		}
		if(m == 0 || n == 0){
			return m == 0 ? n : m;
		}
		int[][] dp = new int[n + 1][m + 1];
		for(int i = 0; i <= m; i++){
			dp[0][i] = i;
		}
		for(int i = 1; i <= n; i++){
			for(int j = 0; j <= m; j++){
				if(j == 0){
					dp[i][j] = i;
					continue;
				}
				if(word1.charAt(i - 1) == word2.charAt(j - 1)){
					dp[i][j] = dp[i - 1][j - 1];
				}else{
					dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
				}
			}
		}
		return dp[n][m];
	}
}
