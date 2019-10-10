package org.wshuai.leetcode;

/**
 * Created by Wei on 10/9/2019.
 * #1219 https://leetcode.com/problems/path-with-maximum-gold/
 */
public class PathWithMaximumGold {
	private int max = 0;
	private int[][] grid;
	private int[][] move;

	public int getMaximumGold(int[][] grid) {
		this.grid = grid;
		move = new int[][]{
				{1, -1, 0, 0},
				{0, 0, 1, -1}
		};
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j] == 0){
					continue;
				}
				dfs(i, j, 0);
			}
		}
		return max;
	}

	private void dfs(int i, int j, int curr){
		int gold = grid[i][j];
		grid[i][j] = 0;
		max = Math.max(curr + gold, max);
		for(int k = 0; k < 4; k++){
			int x = i + move[0][k];
			int y = j + move[1][k];
			if(x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] != 0){
				dfs(x, y, curr + gold);
			}
		}
		grid[i][j] = gold;
	}
}
