package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2023.
 * #2373 https://leetcode.com/problems/largest-local-values-in-a-matrix/
 */
public class LargestLocalValuesInAMatrix {

	// time O(n ^ 2), space O((n - 2) ^ 2)
	public int[][] largestLocal(int[][] grid) {
		int n = grid.length;
		int[][] res = new int[n - 2][n - 2];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 2; j++) {
				grid[i][j] = Math.max(Math.max(grid[i][j], grid[i][j + 1]), grid[i][j + 2]);
			}
		}
		for (int i = 0; i < n - 2; i++) {
			for (int j = 0; j < n - 2; j++) {
				res[i][j] = Math.max(Math.max(grid[i][j], grid[i + 1][j]), grid[i + 2][j]);
			}
		}
		return res;
	}
}
