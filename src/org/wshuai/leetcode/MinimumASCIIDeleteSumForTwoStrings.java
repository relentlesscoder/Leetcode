package org.wshuai.leetcode;

/**
 * Created by Wei on 09/30/2019.
 * #0712 https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
 */
public class MinimumASCIIDeleteSumForTwoStrings {

	// time O(m*n),space O(m*n)
	public int minimumDeleteSum(String s1, String s2) {
		int m = s1.length(), n = s2.length();
		int[][] dp = new int[m + 1][n + 1];
		for(int i = 1; i <= n; i++){
			dp[0][i] = dp[0][i - 1] + s2.charAt(i - 1);
		}
		for(int i = 1; i <= m; i++){
			for(int j = 0; j <= n; j++){
				if(j == 0){
					dp[i][j] = dp[i - 1][j] + s1.charAt(i - 1);
					continue;
				}
				if(s1.charAt(i - 1) == s2.charAt(j - 1)){
					dp[i][j] = dp[i - 1][j - 1];
				}else{
					dp[i][j] = Math.min(dp[i][j - 1] + s2.charAt(j - 1),
						dp[i - 1][j] + s1.charAt(i - 1));
				}
			}
		}
		return dp[m][n];
	}
}
