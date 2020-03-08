package org.wshuai.leetcode;

/**
 * Created by Wei on 07/28/2017.
 * #0329 https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 */
public class LongestIncreasingPathInAMatrix {
	private static final int[] dirs = new int[]{0, 1, 0, -1, 0};

	// time O(r*c), space O(r*c)
	public int longestIncreasingPath(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return 0;
		}
		int res = 1, r = matrix.length, c = matrix[0].length;
		int[][] count = new int[r][c];
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(count[i][j] == 0){
					dfs(i, j, r, c, matrix, count);
				}
			}
		}
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				res = Math.max(res, count[i][j]);
			}
		}
		return res;
	}

	private int dfs(int i, int j, int r, int c, int[][] matrix, int[][] count){
		int next = 0, cur = 0;
		for(int k = 0; k < 4; k++){
			int x = i + dirs[k], y = j + dirs[k + 1];
			if(x < 0 || x >= r || y < 0 || y >= c || matrix[i][j] >= matrix[x][y]){
				continue;
			}
			cur = count[x][y] > 0 ? count[x][y] : dfs(x, y, r, c, matrix, count);
			next = Math.max(cur, next);
		}
		count[i][j] = next + 1;
		return next + 1;
	}
}
