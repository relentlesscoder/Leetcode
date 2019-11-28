package org.wshuai.leetcode;

/**
 * Created by Wei on 10/6/16.
 * #64 https://leetcode.com/problems/minimum-path-sum/
 */
public class MinimumPathSum {
	public int minPathSum(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0){
			return 0;
		}
		int r = grid.length, c = grid[0].length;
		int[][] dp = new int[r][c];
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(i == 0 && j == 0){
					dp[i][j] = grid[i][j];
					continue;
				}
				if(i == 0){
					dp[i][j] = dp[i][j - 1] + grid[i][j];
				}else if(j == 0){
					dp[i][j] = dp[i - 1][j] + grid[i][j];
				}else{
					dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
				}
			}
		}
		return dp[r - 1][c - 1];
	}
}
