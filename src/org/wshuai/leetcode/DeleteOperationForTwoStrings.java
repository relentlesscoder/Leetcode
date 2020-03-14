package org.wshuai.leetcode;

/**
 * Created by Wei on 08/08/2019.
 * #0583 https://leetcode.com/problems/delete-operation-for-two-strings/
 */
public class DeleteOperationForTwoStrings {
	// time O(m*n), space O(m*n)
	public int minDistance(String word1, String word2) {
		int m = word1 == null ? 0 : word1.length();
		int n = word2 == null ? 0 : word2.length();
		if (m == 0) {
			return n;
		}
		if (n == 0) {
			return m;
		}
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			dp[0][i] = i;
		}
		for (int i = 1; i <= m; i++) {
			dp[i][0] = i;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[m][n];
	}
}
