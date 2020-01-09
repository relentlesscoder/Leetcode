package org.wshuai.leetcode;

/**
 * Created by Wei on 11/28/2019.
 * #0063 https://leetcode.com/problems/unique-paths-ii/
 */
public class UniquePathsII {
	// time O(r*c), space O(r*c) can be improved to O(c)
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if(obstacleGrid == null
				|| obstacleGrid.length == 0
				|| obstacleGrid[0].length == 0){
			return 0;
		}
		int r = obstacleGrid.length;
		int c = obstacleGrid[0].length;
		if(obstacleGrid[0][0] == 1 || obstacleGrid[r - 1][c - 1] == 1){
			return 0;
		}
		int[][] dp = new int[r][c];
		dp[0][0] = 1;
		for(int i = 1; i < c; i++){
			dp[0][i] = obstacleGrid[0][i] == 1 ? 0 : dp[0][i - 1];
		}
		for(int i = 1; i < r; i++){
			for(int j = 0; j < c; j++){
				if(j == 0){
					dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i - 1][0];
					continue;
				}
				dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
			}
		}
		return dp[r - 1][c - 1];
	}
}
