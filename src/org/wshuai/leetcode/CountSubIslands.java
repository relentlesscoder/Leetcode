package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2023.
 * #1905 https://leetcode.com/problems/count-sub-islands/
 */
public class CountSubIslands {

	private static final int[] DIRS = new int[] {0, 1, 0, -1, 0};

	// time O(m * n), space O(m * n)
	public int countSubIslands(int[][] grid1, int[][] grid2) {
		int res = 0, m = grid1.length, n = grid1[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid2[i][j] == 1) {
					if (findIsland(grid1, grid2, i, j, m, n)) {
						res++;
					}
				}
			}
		}
		return res;
	}

	private boolean findIsland(int[][] grid1, int[][] grid2, int i, int j, int m, int n) {
		boolean res = grid1[i][j] == 1; // just need to check if [i,j] in grid1 is also land
		grid2[i][j] = 0;
		for (int k = 0; k < 4; k++) {
			int x = i + DIRS[k], y = j + DIRS[k + 1];
			if (x < 0 || x >= m || y < 0 || y >= n || grid2[x][y] != 1) {
				continue;
			}
			if (!findIsland(grid1, grid2, x, y, m, n)) {
				res = false;
			}
		}
		return res;
	}
}
