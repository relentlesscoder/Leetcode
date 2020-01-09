package org.wshuai.leetcode;

/**
 * Created by Wei on 10/06/2016.
 * #0064 https://leetcode.com/problems/minimum-path-sum/
 */
public class MinimumPathSum {
	// time O(r*c), space O(1)
	public int minPathSum(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0){
			return 0;
		}
		int r = grid.length;
		int c = grid[0].length;
		for(int i = 1; i < c; i++){
			grid[0][i] += grid[0][i - 1];
		}
		for(int i = 1; i < r; i++){
			for(int j = 0; j < c; j++){
				if(j == 0){
					grid[i][j] += grid[i - 1][j];
					continue;
				}
				grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
			}
		}
		return grid[r - 1][c - 1];
	}
}
