package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/16.
 * #361 https://leetcode.com/problems/bomb-enemy/
 */
public class BombEnemy {

	public int maxKilledEnemies(char[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0){
			return 0;
		}
		int res = 0;
		int r = grid.length, c = grid[0].length;
		int[][][] dp = new int[r][c][4];
		for(int i = 0; i < r; ++i){
			for(int j = 0; j < c; ++j){
				if(grid[i][j] == 'W'){
					continue;
				}
				if(grid[i][j] == 'E'){
					dp[i][j][0]++;
				}
				if(i > 0){
					dp[i][j][0] += dp[i - 1][j][0];
				}
			}
		}
		for(int j = 0; j < c; ++j){
			for(int i = 0; i < r; ++i){
				if(grid[i][j] == 'W'){
					continue;
				}
				if(grid[i][j] == 'E'){
					dp[i][j][1]++;
				}
				if(j > 0){
					dp[i][j][1] += dp[i][j - 1][1];
				}
			}
		}
		for(int j = c - 1; j >= 0; --j){
			for(int i = 0; i < r; ++i){
				if(grid[i][j] == 'W'){
					continue;
				}
				if(grid[i][j] == 'E'){
					dp[i][j][2]++;
				}
				if(j < c - 1){
					dp[i][j][2] += dp[i][j + 1][2];
				}
			}
		}
		for(int i = r - 1; i >= 0; --i){
			for(int j = 0; j < c; ++j){
				if(grid[i][j] == 'W'){
					continue;
				}
				if(grid[i][j] == 'E'){
					dp[i][j][3]++;
				}
				if(i < r - 1){
					dp[i][j][3] += dp[i + 1][j][3];
				}
				if(grid[i][j] == '0'){
					res = Math.max(res, dp[i][j][0] + dp[i][j][1] + dp[i][j][2] + dp[i][j][3]);
				}
			}
		}
		return res;
	}
}
