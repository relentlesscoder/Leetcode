package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/28/19.
 * #62 https://leetcode.com/problems/unique-paths/
 */
public class UniquePaths {

	public int uniquePaths(int m, int n) {
		int[][] dp = new int[m][n];
		Arrays.fill(dp[0], 1);
		for(int i = 1; i < m; i++){
			dp[i][0] = 1;
			for(int j = 1; j < n; j++){
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}
		return dp[m - 1][n - 1];
	}
}
