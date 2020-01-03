package org.wshuai.leetcode;

/**
 * Created by Wei on 11/28/19.
 * #63 https://leetcode.com/problems/unique-paths-ii/
 */
public class UniquePathsII {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if(obstacleGrid == null
				|| obstacleGrid.length == 0
				|| obstacleGrid[0].length == 0){
			return 0;
		}
		int r = obstacleGrid.length;
		int c = obstacleGrid[0].length;
		if(obstacleGrid[0][0] == 1
				|| obstacleGrid[r - 1][c - 1] == 1){
			return 0;
		}
		int[][] dp = new int[r][c];
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(obstacleGrid[i][j] == 1){
					dp[i][j] = 0;
					continue;
				}
				if(i == 0 && j == 0){
					dp[i][j] = 1;
				}else if(i == 0){
					dp[i][j] = dp[i][j - 1];
				}else if(j == 0){
					dp[i][j] = dp[i - 1][j];
				}else{
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				}
			}
		}
		return dp[r - 1][c - 1];
	}
}
