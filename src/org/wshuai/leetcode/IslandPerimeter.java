package org.wshuai.leetcode;

/**
 * Created by Wei on 11/21/2016.
 * #0463 https://leetcode.com/problems/island-perimeter/
 */
public class IslandPerimeter {

	private static final int[] DIRECTIONS = new int[]{0, -1, 0, 1, 0};

	// time O(m*n)
	public int islandPerimeter(int[][] grid) {
		int res = 0, m = grid.length, n = grid[0].length;
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == 0){
					continue;
				}
				for(int k = 0; k < 4; k++){
					int x = i + DIRECTIONS[k], y = j + DIRECTIONS[k + 1];
					if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0){
						res++;
					}
				}
			}
		}
		return res;
	}
}
