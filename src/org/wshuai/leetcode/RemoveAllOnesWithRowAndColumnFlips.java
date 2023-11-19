package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2023.
 * #2128 https://leetcode.com/problems/remove-all-ones-with-row-and-column-flips/
 */
public class RemoveAllOnesWithRowAndColumnFlips {

	// time O(m * n), space O(n)
	public boolean removeOnes(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[] original = new int[n], reverse = new int[n];
		for (int i = 0; i < n; i++) {
			original[i] = grid[0][i];
			reverse[i] = 1- grid[0][i];
		}
		for (int i = 1; i < m; i++) {
			int[] key = grid[i][0] == original[0] ? original : reverse;
			for (int j = 1; j < n; j++) {
				if (grid[i][j] != key[j]) {
					return false;
				}
			}
		}
		return true;
	}
}
