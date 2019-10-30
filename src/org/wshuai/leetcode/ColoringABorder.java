package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/28/2019.
 * #1034 https://leetcode.com/problems/coloring-a-border/
 */
public class ColoringABorder {
	private int[][] dir = new int[][]{
			{1, -1, 0, 0},
			{0, 0, 1, -1}
	};

	public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
		List<int[]> border = new ArrayList<>();
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		dfs(grid, r0, c0, grid[r0][c0], visited, border);
		for(int[] c: border){
			grid[c[0]][c[1]] = color;
		}
		return grid;
	}

	private void dfs(int[][] grid, int i, int j, int color, boolean[][] visited, List<int[]> border){
		visited[i][j] = true;
		boolean isBoundary = false;
		if(i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length - 1){
			isBoundary = true;
		}
		List<int[]> next = new ArrayList<>();
		int count = 0;
		for(int k = 0; k < 4; k++){
			int x = i + dir[0][k];
			int y = j + dir[1][k];
			if(x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == color){
				count++;
				if(!visited[x][y]){
					next.add(new int[]{x, y});
				}
			}
		}
		if(isBoundary || count < 4){
			border.add(new int[]{i, j});
		}
		for(int[] c: next){
			dfs(grid, c[0], c[1], color, visited, border);
		}
	}
}
