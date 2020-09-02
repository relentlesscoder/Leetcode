package org.wshuai.leetcode;

/**
 * Created by Wei on 09/01/2020.
 * #1559 https://leetcode.com/problems/detect-cycles-in-2d-grid/
 */
public class DetectCyclesIn2DGrid {

	private static final int[] dirs = new int[]{0, -1, 0, 1, 0};

	// time O(m*n)
	public boolean containsCycle(char[][] grid) {
		int m = grid.length, n = grid[0].length;
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				// skip visited nodes
				if(grid[i][j] == '-'){
					continue;
				}
				char val = grid[i][j];
				// mark currently visiting nodes
				grid[i][j] = '*';
				for(int k = 0; k < 4; k++){
					int x = i + dirs[k], y = j + dirs[k + 1];
					if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == val){
						if(hasCycle(x, y, i, j, val, grid)){
							return true;
						}
					}
				}
				// cannot find a cycle, mark all currently visiting nodes as visited
				markVisited(i, j, grid);
			}
		}
		return false;
	}

	private void markVisited(int i, int j, char[][] grid){
		grid[i][j] = '-';
		for(int k = 0; k < 4; k++){
			int m = i + dirs[k], n = j + dirs[k + 1];
			if(m >= 0 && m < grid.length && n >= 0 && n < grid[0].length && grid[m][n] == '*'){
				markVisited(m, n, grid);
			}
		}
	}

	private boolean hasCycle(int i, int j, int xp, int yp, char val, char[][] grid){
		grid[i][j] = '*';
		for(int k = 0; k < 4; k++){
			int m = i + dirs[k], n = j + dirs[k + 1];
			if(m < 0 || m >= grid.length || n < 0 || n >= grid[0].length){
				continue;
			}
			if(m == xp && n == yp){
				continue;
			}
			// once we hit a previously visited node, a cycle is detected
			if(grid[m][n] == '*'){
				return true;
			}
			if(grid[m][n] == val && hasCycle(m, n, i, j, val, grid)){
				return true;
			}
		}
		return false;
	}
}
