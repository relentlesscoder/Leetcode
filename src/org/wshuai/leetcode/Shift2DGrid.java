package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/18/19.
 * #1260 https://leetcode.com/problems/shift-2d-grid/
 */
public class Shift2DGrid {
	public List<List<Integer>> shiftGrid(int[][] grid, int k) {
		List<List<Integer>> res = new ArrayList<>();
		int R = grid.length;
		int C = grid[0].length;
		Integer[][] temp = new Integer[R][C];
		for(int i = 0; i < R; i++){
			for(int j = 0; j < C; j++){
				int v = i * C + j + k;
				int x = (v / C) % R;
				int y = (v % C);
				temp[x][y] = grid[i][j];
			}
		}
		for(int i = 0; i < R; i++){
			res.add(Arrays.asList(temp[i]));
		}
		return res;
	}
}
