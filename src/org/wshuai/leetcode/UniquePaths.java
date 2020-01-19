package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/28/2019.
 * #0062 https://leetcode.com/problems/unique-paths/
 */
public class UniquePaths {
	// time O(r*c), space O(r*c) can be improved to O(c)
	public int uniquePaths(int m, int n) {
		if(m == 0 || n == 0){
			return 0;
		}
		int[][] dp = new int[m][n];
		Arrays.fill(dp[0], 1);
		for(int i = 1; i < m; i++){
			for(int j = 0; j < n; j++){
				if(j == 0){
					dp[i][j] = 1;
					continue;
				}
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}
		return dp[m - 1][n - 1];
	}
}
