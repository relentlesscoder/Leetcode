package org.wshuai.leetcode;

/**
 * Created by Wei on 09/24/2016.
 * #0200 https://leetcode.com/problems/number-of-islands/#/description
 */
public class NumberOfIslands {
	private static int[][] dirs = new int[][]{
		{1, -1, 0, 0},
		{0, 0, 1, -1}
	};

	// time O(r*c)
	public int numIslands(char[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0){
			return 0;
		}
		int res = 0, r = grid.length, c = grid[0].length;
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(grid[i][j] == '1'){
					res++;
					dfs(i, j, r, c, grid);
				}
			}
		}
		return res;
	}

	private void dfs(int i, int j, int r, int c, char[][] grid){
		if(i < 0 || i >= r || j < 0 || j >= c || grid[i][j] != '1'){
			return;
		}
		grid[i][j] = '0';
		for(int k = 0; k < 4; k++){
			int x = i + dirs[0][k];
			int y = j + dirs[1][k];
			dfs(x, y, r, c, grid);
		}
	}
}
