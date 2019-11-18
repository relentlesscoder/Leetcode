package org.wshuai.leetcode;

/**
 * Created by Wei on 11/17/2019.
 * #803 https://leetcode.com/problems/bricks-falling-when-hit/
 */
public class BricksFallingWhenHit {
	private int[][] dirs = new int[][]{
		{1, -1, 0, 0},
		{0, 0, 1, -1}
	};

	// https://leetcode.com/problems/bricks-falling-when-hit/discuss/119829/Python-Solution-by-reversely-adding-hits-bricks-back
	public int[] hitBricks(int[][] grid, int[][] hits) {
		int H = hits.length;
		int R = grid.length;
		int C = grid[0].length;
		int[] res = new int[H];
		for(int[] h : hits){
			grid[h[0]][h[1]]--;
		}
		for(int i = 0; i < C; i++){
			dfs(0, i, R, C, grid);
		}
		for(int h = H - 1; h >= 0; h--){
			int i = hits[h][0];
			int j = hits[h][1];
			grid[i][j]++;
			if(grid[i][j] == 1 && isConnected(i, j, R, C, grid)){
				res[h] = dfs(i, j, R, C, grid) - 1;
			}
		}
		return res;
	}

	private boolean isConnected(int i, int j, int r, int c, int[][] grid){
		if(i == 0){
			return true;
		}
		for(int k = 0; k < 4; k++){
			int x = i + dirs[0][k];
			int y = j + dirs[1][k];
			if(x >= 0 && y >= 0 && x < r && y < c && grid[x][y] == 2){
				return true;
			}
		}
		return false;
	}

	private int dfs(int i, int j, int r, int c, int[][] grid){
		if(i < 0 || j < 0 || i >= r || j >= c || grid[i][j] != 1){
			return 0;
		}
		grid[i][j] = 2;
		int res = 1;
		for(int k = 0; k < 4; k++){
			int x = i + dirs[0][k];
			int y = j + dirs[1][k];
			res += dfs(x, y, r, c, grid);
		}
		return res;
	}
}
