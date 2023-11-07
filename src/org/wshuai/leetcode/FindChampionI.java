package org.wshuai.leetcode;

/**
 * Created by Wei on 11/06/2023.
 * #2923 https://leetcode.com/problems/find-champion-i/
 */
public class FindChampionI {

	// time O(m * n), space O(1)
	public int findChampion(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		for (int i = 0; i < m; i++) {
			boolean isChampion = true;
			for (int j = 0; j < n; j++) {
				if (i != j && grid[i][j] == 0) {
					isChampion = false;
					break;
				}
			}
			if (isChampion) {
				return i;
			}
		}
		return -1;
	}
}
