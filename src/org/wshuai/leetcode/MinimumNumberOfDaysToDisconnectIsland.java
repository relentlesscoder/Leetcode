package org.wshuai.leetcode;

/**
 * Created by Wei on 09/01/2020.
 * #1568 https://leetcode.com/problems/minimum-number-of-days-to-disconnect-island/
 */
public class MinimumNumberOfDaysToDisconnectIsland {

	private static final int[] dirs = new int[]{0, 1, 0, -1, 0};

	// time O((m*n)^2)
	public int minDays(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int islands = countIslands(grid, m, n);
		if(islands == 0 || islands > 1){
			return 0;
		}else{
			for(int i = 0; i < m; i++){
				for(int j = 0; j < n; j++){
					if(grid[i][j] == 0){
						continue;
					}
					grid[i][j] = 0;
					if(countIslands(grid, m, n) > 1){
						return 1;
					}
					grid[i][j] = 1;
				}
			}
		}
		return 2;
	}

	private int countIslands(int[][] grid, int m, int n){
		int islands = 0;
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] != 1){
					continue;
				}
				dfs(grid, i, j, m, n);
				islands++;
			}
		}
		// reset grid
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] > 0){
					grid[i][j] = 1;
				}
			}
		}
		return islands;
	}

	private void dfs(int[][] grid, int i, int j, int m, int n){
		// mark visited
		grid[i][j] = 2;
		for(int k = 0; k < 4; k++){
			int x = i + dirs[k], y = j + dirs[k + 1];
			if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1){
				continue;
			}
			dfs(grid, x, y, m, n);
		}
	}
}
