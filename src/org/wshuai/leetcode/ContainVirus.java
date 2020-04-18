package org.wshuai.leetcode;

/**
 * Created by Wei on 12/24/2019.
 * #0749 https://leetcode.com/problems/contain-virus/
 */
public class ContainVirus {

	private static final int[] dirs = new int[]{0, 1, 0, -1, 0};

	public int containVirus(int[][] grid) {
		int res = 0, m = grid.length, n = grid[0].length;
		while(true){
			int walls = buildWalls(grid, m, n);
			res += walls;
			if(walls == 0){
				break;
			}
		}
		return res;
	}

	private int buildWalls(int[][] grid, int m, int n){
		int[][] visited = new int[m][n];
		int res = 0, maxArea = 0, color = -1, row = -1, col = -1;
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == 1 && visited[i][j] == 0){
					int[] walls = new int[]{0};
					int area = detectInfectedArea(i, j, m, n, color, grid, visited, walls);
					if(area > maxArea){
						maxArea = area;
						res = walls[0];
						row = i;
						col = j;
					}
					color--;
				}
			}
		}
		markInfectedArea(row, col, m, n, grid);
		visited = new int[m][n];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == 1 && visited[i][j] != 1){
					spread(i, j, grid, visited, m, n);
				}
			}
		}
		return res;
	}

	private void spread(int i, int j, int[][] grid, int[][] visited, int m, int n){
		if(i < 0 || i >= m || j < 0 || j >= n || visited[i][j] == 1){
			return;
		}
		// important, already contained cell should not be visited again
		if(grid[i][j] == -1){
			return;
		}
		visited[i][j] = 1;
		if(grid[i][j] == 0){
			grid[i][j] = 1;
			return;
		}
		for(int k = 0; k < 4; k++){
			int x = i + dirs[k], y = j + dirs[k + 1];
			spread(x, y, grid, visited, m, n);
		}
	}

	private int detectInfectedArea(int i, int j, int m, int n, int color, int[][] grid, int[][] visited, int[] walls){
		if(i < 0 || i >= m || j < 0 || j >= n){
			return 0;
		}
		if(grid[i][j] == 0){
			// each time we meet 0 (from up, down left or right),
			// we need add 1 more wall
			walls[0]++;
			// use color to mark the current infected area so that
			// the cell will not be double counted
			if(visited[i][j] == color){
				return 0;
			}
			visited[i][j] = color;
			return 1;
		}
		if(grid[i][j] != 1 || visited[i][j] == 1){
			return 0;
		}
		visited[i][j] = 1;
		int res = 0;
		for(int k = 0; k < 4; k++){
			int x = i + dirs[k], y = j + dirs[k + 1];
			res += detectInfectedArea(x, y, m, n, color, grid, visited, walls);
		}
		return res;
	}

	private void markInfectedArea(int i, int j, int m, int n, int[][] grid){
		if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1){
			return;
		}
		// mark the cell as contained
		grid[i][j] = -1;
		for(int k = 0; k < 4; k++){
			int x = i + dirs[k], y = j + dirs[k + 1];
			markInfectedArea(x, y, m, n, grid);
		}
	}
}
