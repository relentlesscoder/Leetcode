package org.wshuai.leetcode;

/**
 * Created by Wei on 09/21/2023.
 * #2482 https://leetcode.com/problems/difference-between-ones-and-zeros-in-row-and-column/
 */
public class DifferenceBetweenOnesAndZerosInRowAndColumn {

	// time O(m * n), space O(m + n)
	public int[][] onesMinusZeros(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[] onesInRow = new int[m], onesInCol = new int[n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					onesInRow[i]++;
					onesInCol[j]++;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = 2 * onesInRow[i] - n + 2 * onesInCol[j] - m;
			}
		}
		return grid;
	}
}
