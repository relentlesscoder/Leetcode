package org.wshuai.leetcode;

/**
 * Created by Wei on 11/17/2019.
 * #0803 https://leetcode.com/problems/bricks-falling-when-hit/
 */
public class BricksFallingWhenHit {
	private static final int[] dirs = new int[]{0, 1, 0, -1, 0};

	// time O(m*n)
	// https://leetcode.com/problems/bricks-falling-when-hit/discuss/119829/Python-Solution-by-reversely-adding-hits-bricks-back
	public int[] hitBricks(int[][] grid, int[][] hits) {
		// grid status:
		// 0 -> empty, 1 -> unvisited, 2 -> visited
		int k = hits.length, m = grid.length, n = grid[0].length;
		int[] res = new int[k];
		for(int[] h : hits){
			// mark brick cells as 0, empty cell as 1 for each hit position
			grid[h[0]][h[1]]--;
		}
		for(int j = 0; j < n; j++){
			// for each brick cells on the top row, dfs to search all connected
			// bricks, the grid will be the final status after all hits after
			// this step
			dfs(0, j, m, n, grid);
		}
		for(int i = k - 1; i >= 0; i--){
			int x = hits[i][0], y = hits[i][1];
			// recover hits cells (connected only) reversely and dfs to add all
			// bricks back.
			// note that if it is not connected meaning it is already dropped by
			// previous hits.
			if(++grid[x][y] == 1 && isConnected(x, y, m, n, grid)){
				res[i] = dfs(x, y, m, n, grid) - 1;
			}
		}
		return res;
	}

	private int dfs(int i, int j, int m, int n, int[][] grid){
		if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1){
			return 0;
		}
		grid[i][j] = 2;
		int res = 1;
		for(int k = 0; k < 4; k++){
			int x = i + dirs[k], y = j + dirs[k + 1];
			res += dfs(x, y, m, n, grid);
		}
		return res;
	}

	private boolean isConnected(int i, int j, int m, int n, int[][] grid){
		if(i == 0){
			return true;
		}
		for(int k = 0; k < 4; k++){
			int x = i + dirs[k], y = j + dirs[k + 1];
			if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 2){
				return true;
			}
		}
		return false;
	}
}
