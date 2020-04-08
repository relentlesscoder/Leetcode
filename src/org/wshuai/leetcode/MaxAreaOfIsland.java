package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2019.
 * #0695 https://leetcode.com/problems/max-area-of-island/
 */
public class MaxAreaOfIsland {

	private static final int[] dirs = new int[]{0, 1, 0, -1, 0};

	// time O(m*n), space O(1)
	public int maxAreaOfIsland(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0){
			return 0;
		}
		int res = 0, m = grid.length, n = grid[0].length;
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == 1){
					res = Math.max(res, dfs(i, j, m, n, grid));
				}
			}
		}
		return res;
	}

	private int dfs(int i, int j, int m, int n, int[][] grid){
		grid[i][j] = 0;
		int res = 1;
		for(int k = 0; k < 4; k++){
			int x = i + dirs[k], y = j + dirs[k + 1];
			if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0){
				continue;
			}
			res += dfs(x, y, m, n, grid);
		}
		return res;
	}
}
