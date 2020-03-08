package org.wshuai.leetcode;

/**
 * Created by Wei on 11/21/2016.
 * #0463 https://leetcode.com/problems/island-perimeter/
 */
public class IslandPerimeter {
	// time O(m*n)
	public int islandPerimeter(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0){
			return 0;
		}
		int[] dirs = new int[]{0, 1, 0, -1, 0};
		int res = 0, m = grid.length, n = grid[0].length;
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == 0){
					continue;
				}
				for(int k = 0; k < 4; k++){
					int x = i + dirs[k], y = j + dirs[k + 1];
					if(x < 0 || x >= m || y < 0 || y >= n){
						res++;
						continue;
					}
					if(grid[x][y] == 0){
						res++;
					}
				}
			}
		}
		return res;
	}
}
