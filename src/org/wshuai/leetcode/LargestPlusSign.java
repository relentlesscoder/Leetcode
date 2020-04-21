package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/06/2019.
 * #0764 https://leetcode.com/problems/largest-plus-sign/
 */
public class LargestPlusSign {
	// time O(N^2), space O(N^2)
	public int orderOfLargestPlusSign(int N, int[][] mines) {
		int res = 0;
		int[][] grid = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(grid[i], 1);
		}
		for (int[] m : mines) {
			grid[m[0]][m[1]] = 0;
		}
		for (int i = 0; i < N; i++) {
			int ones = 0;
			for (int j = 0; j < N; j++) {
				ones = grid[i][j] == 0 ? 0 : ones + 1;
				grid[i][j] = ones;
			}
			ones = 0;
			for (int j = N - 1; j >= 0; j--) {
				ones = grid[i][j] == 0 ? 0 : ones + 1;
				grid[i][j] = Math.min(grid[i][j], ones);
			}
		}
		for (int j = 0; j < N; j++) {
			int ones = 0;
			for (int i = 0; i < N; i++) {
				ones = grid[i][j] == 0 ? 0 : ones + 1;
				grid[i][j] = Math.min(grid[i][j], ones);
			}
			ones = 0;
			for (int i = N - 1; i >= 0; i--) {
				ones = grid[i][j] == 0 ? 0 : ones + 1;
				grid[i][j] = Math.min(grid[i][j], ones);
				res = Math.max(res, grid[i][j]);
			}
		}
		return res;
	}
}
