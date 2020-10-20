package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/05/2019.
 * #0885 https://leetcode.com/problems/spiral-matrix-iii/
 */
public class SpiralMatrixIII {

	private static final int[][] DIRECTIONS = new int[][]{
			{0, 1}, {1, 0}, {0, -1}, {-1, 0}
	};

	// time O(R*C), space O(R*C)
	public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
		List<int[]> res = new ArrayList<>();
		int steps = 0, dir = 0, size = R * C;
		res.add(new int[]{r0, c0});
		while(res.size() < size){
			if(dir == 0 || dir == 2){
				steps++;
			}
			for(int i = 0; i < steps; i++){
				r0 += DIRECTIONS[dir][0];
				c0 += DIRECTIONS[dir][1];
				if(r0 >= 0 && r0 < R && c0 >= 0 && c0 < C){
					res.add(new int[]{r0, c0});
				}
			}
			dir = (dir + 1) % 4;
		}
		return res.toArray(new int[size][2]);
	}
}
