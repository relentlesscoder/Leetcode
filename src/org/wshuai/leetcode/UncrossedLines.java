package org.wshuai.leetcode;

/**
 * Created by Wei on 10/24/19.
 * #1035 https://leetcode.com/problems/uncrossed-lines/
 */
public class UncrossedLines {

	// same as LCS
	public int maxUncrossedLines(int[] A, int[] B) {
		int m = A.length, n = B.length;
		int[][] dp = new int[m + 1][n + 1];
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= n; j++){
				if(A[i - 1] == B[j - 1]){
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}else{
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}
		return dp[m][n];
	}
}
