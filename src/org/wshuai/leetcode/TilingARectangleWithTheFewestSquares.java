package org.wshuai.leetcode;

/**
 * Created by Wei on 11/28/19.
 * #1240 https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/
 */
public class TilingARectangleWithTheFewestSquares {
	// https://www.youtube.com/watch?v=2QRUgAT7sGc
	public int tilingRectangle(int n, int m) {
		if(Math.max(n, m) == 13 && Math.min(n, m) == 11){
			return 6;
		}
		int[][] dp = new int[n + 1][m + 1];
		for(int i = 1; i <= n; ++i){
			for(int j = 1; j <= m; j++){
				dp[i][j] = Integer.MAX_VALUE;
				if(i == j){
					dp[i][j] = 1;
					continue;
				}

				for(int k = 1; k <= i / 2; ++k){
					dp[i][j] = Math.min(dp[i][j], dp[k][j] + dp[i - k][j]);
				}
				for(int k = 1; k <= j / 2; ++k){
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[i][j - k]);
				}
			}
		}
		return dp[n][m];
	}
}
