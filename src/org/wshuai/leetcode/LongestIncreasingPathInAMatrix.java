package org.wshuai.leetcode;

/**
 * Created by Wei on 07/28/2017.
 * #0329 https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 */
public class LongestIncreasingPathInAMatrix {

	private static final int[] DIRECTIONS = new int[]{0, -1, 0, 1, 0};

	// time O(m*n), space O(m*n)
	public int longestIncreasingPath(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return 0;
		}
		int res = 1, m = matrix.length, n = matrix[0].length;
		int[][] count = new int[m][n];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(count[i][j] == 0){
					dfs(i, j, matrix, count);
				}
			}
		}
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				res = Math.max(res, count[i][j]);
			}
		}
		return res;
	}

	private int dfs(int i, int j, int[][] matrix, int[][] count){
		int longest = 0;
		for(int k = 0; k < 4; k++){
			int x = i + DIRECTIONS[k], y = j + DIRECTIONS[k + 1];
			if(x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[i][j] >= matrix[x][y]){
				continue;
			}
			longest = Math.max(longest, count[x][y] > 0 ? count[x][y] : dfs(x, y, matrix, count));
		}
		count[i][j] = longest + 1;
		return count[i][j];
	}
}
