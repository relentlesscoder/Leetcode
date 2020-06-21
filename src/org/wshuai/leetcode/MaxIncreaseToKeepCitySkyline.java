package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/24/2019.
 * #0807 https://leetcode.com/problems/max-increase-to-keep-city-skyline/
 */
public class MaxIncreaseToKeepCitySkyline {
	// time O(m*n), space O(m+n)
	public int maxIncreaseKeepingSkyline(int[][] grid) {
		int res = 0, m = grid.length, n = grid[0].length;
		int[] rowMax = new int[m], colMax = new int[n];
		Arrays.fill(rowMax, -1);
		Arrays.fill(colMax, -1);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				rowMax[i] = Math.max(rowMax[i], grid[i][j]);
				colMax[j] = Math.max(colMax[j], grid[i][j]);
			}
		}
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				res += Math.min(rowMax[i], colMax[j]) - grid[i][j];
			}
		}
		return res;
	}
}
