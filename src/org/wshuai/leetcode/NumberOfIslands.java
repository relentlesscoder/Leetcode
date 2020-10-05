package org.wshuai.leetcode;

/**
 * Created by Wei on 09/24/2016.
 * #0200 https://leetcode.com/problems/number-of-islands/#/description
 */
public class NumberOfIslands {

	private static final int[] DIRECTION = new int[]{0, -1, 0, 1, 0};

	// time O(m*n)
	public int numIslands(char[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0){
			return 0;
		}
		int m = grid.length, n = grid[0].length, res = 0;
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == '1'){
					dfs(i, j, m, n, grid);
					res++;
				}
			}
		}
		return res;
	}

	private void dfs(int i, int j, int m, int n, char[][] grid){
		grid[i][j] = '0';
		for(int k = 0; k < 4; k++){
			int x = i + DIRECTION[k], y = j + DIRECTION[k + 1];
			if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '0'){
				continue;
			}
			dfs(x, y, m, n, grid);
		}
	}
}
