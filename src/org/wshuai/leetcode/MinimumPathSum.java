package org.wshuai.leetcode;

/**
 * Created by Wei on 10/6/16.
 */
public class MinimumPathSum {
	public int minPathSum(int[][] grid) {
		if (grid == null) {
			return 0;
		}

		int rows = grid.length;
		int cols = grid[0].length;
		int[][] mtx = new int[rows][cols];
		mtx[0][0] = grid[0][0];
		for (int i = 1; i < cols; i++) {
			mtx[0][i] = mtx[0][i - 1] + grid[0][i];
		}
		for (int i = 1; i < rows; i++) {
			mtx[i][0] = mtx[i - 1][0] + grid[i][0];
		}
		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < cols; j++) {
				int up = mtx[i - 1][j];
				int left = mtx[i][j - 1];
				int min = up < left ? up : left;
				mtx[i][j] = min + grid[i][j];
			}
		}
		return mtx[rows - 1][cols - 1];
	}
}
