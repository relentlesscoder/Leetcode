package org.wshuai.leetcode;

/**
 * Created by Wei on 08/08/2019.
 * #0583 https://leetcode.com/problems/delete-operation-for-two-strings/
 */
public class DeleteOperationForTwoStrings {

	// time O(m*n), space O(m*n)
	public int minDistance(String word1, String word2) {
		int m = word1.length(), n = word2.length();
		int[][] dp = new int[m + 1][n + 1];
		for(int i = 1; i <= n; i++){
			dp[0][i] = i;
		}
		for(int i = 1; i <= m; i++){
			for(int j = 0; j <= n; j++){
				if(j == 0){
					dp[i][j] = i;
					continue;
				}
				if(word1.charAt(i - 1) == word2.charAt(j - 1)){
					dp[i][j] = dp[i - 1][j - 1];
				}else{
					dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
				}
			}
		}
		return dp[m][n];
	}
}
