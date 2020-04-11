package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/09/2019.
 * #0694 https://leetcode.com/problems/number-of-distinct-islands/
 */
public class NumberOfDistinctIslands {

	private static final int[] dirs = new int[]{0, 1, 0, -1, 0};

	// time O(m*n), space O(1)
	public int numDistinctIslands(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0){
			return 0;
		}
		int m = grid.length, n = grid[0].length;
		Set<String> islands = new HashSet<>();
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == 1){
					StringBuilder sb = new StringBuilder();
					dfs(i, j, i, j, m, n, grid, sb);
					islands.add(sb.toString());
				}
			}
		}
		return islands.size();
	}

	private void dfs(int r, int c, int i, int j, int m, int n, int[][] grid, StringBuilder sb){
		grid[r][c] = 0;
		int hash = (r - i) * n + (c - j);
		sb.append(hash);
		for(int k = 0; k < 4; k++){
			int x = r + dirs[k], y = c + dirs[k + 1];
			if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0){
				continue;
			}
			dfs(x, y, i, j, m, n, grid, sb);
		}
	}
}
