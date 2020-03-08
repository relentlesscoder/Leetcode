package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/29/2016.
 * #0417 https://leetcode.com/problems/pacific-atlantic-water-flow/
 */
public class PacificAtlanticWaterFlow {
	private static final int[] dirs = new int[]{0, 1, 0, -1, 0};

	// time O(m*n), space O(m*n)
	public List<List<Integer>> pacificAtlantic(int[][] matrix) {
		List<List<Integer>> res = new ArrayList<>();
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return res;
		}
		int m = matrix.length, n = matrix[0].length;
		boolean[][] pacific = new boolean[m][n], atlantic = new boolean[m][n];
		for(int i = 0; i < n; i++){
			dfs(0, i, m, n, pacific, matrix);
			dfs(m - 1, i, m, n, atlantic, matrix);
		}
		for(int i = 0; i < m; i++){
			dfs(i, 0, m, n, pacific, matrix);
			dfs(i, n - 1, m, n, atlantic, matrix);
		}
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(pacific[i][j] && atlantic[i][j]){
					res.add(Arrays.asList(i, j));
				}
			}
		}
		return res;
	}

	private void dfs(int i, int j, int m, int n, boolean[][] visited, int[][] matrix){
		visited[i][j] = true;
		for(int k = 0; k < 4; k++){
			int x = i + dirs[k], y = j + dirs[k + 1];
			if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && matrix[i][j] <= matrix[x][y]){
				dfs(x, y, m, n, visited, matrix);
			}
		}
	}
}
