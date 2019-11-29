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
		int[][] dp = new int[2][c];
		int prev = 0, curr = 0;
		for(int i = 0; i < r; i++){
			prev = curr;
			curr = 1 - curr;

			for(int j = 0; j < c; j++){
				if(i == 0 && j == 0){
					dp[curr][j] = grid[i][j];
					continue;
				}
				dp[curr][j] = Integer.MAX_VALUE;
				if(i > 0){
					dp[curr][j] = Math.min(dp[prev][j] + grid[i][j], dp[curr][j]);
				}
				if(j > 0){
					dp[curr][j] = Math.min(dp[curr][j - 1] + grid[i][j], dp[curr][j]);
				}
			}
		}
		return dp[curr][c - 1];
	}

	public int minPathSum2D(int[][] grid) {
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
				dp[i][j] = Integer.MAX_VALUE;
				if(i > 0){
					dp[i][j] = Math.min(dp[i - 1][j] + grid[i][j], dp[i][j]);
				}
				if(j > 0){
					dp[i][j] = Math.min(dp[i][j - 1] + grid[i][j], dp[i][j]);
				}
			}
		}
		return dp[r - 1][c - 1];
	}
}
