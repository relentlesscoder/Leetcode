package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/17/2019.
 * #778 https://leetcode.com/problems/swim-in-rising-water/
 */
public class SwimInRisingWater {
	public int swimInWater(int[][] grid) {
		int[][] dirs = new int[][]{
			{1, -1, 0, 0},
			{0, 0, 1, -1}
		};
		int N = grid.length;
		int t = N * N - 1;
		int[] root = new int[N * N];
		for(int i = 0; i < root.length; i++){
			root[i] = i;
		}
		Map<Integer, int[]> map = new HashMap<>();
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				map.put(grid[i][j], new int[]{i, j, i * N + j});
			}
		}
		for(int i = 0; i < N * N; i++){
			int[] pos = map.get(i);
			for(int k = 0; k < 4; k++){
				int x = pos[0] + dirs[0][k];
				int y = pos[1] + dirs[1][k];
				if(x >= 0 && y >= 0 && x < N && y < N && grid[x][y] <= i){
					int r1 = find(pos[2], root);
					int r2 = find(map.get(grid[x][y])[2], root);
					if((r1 == 0 && r2 == t) || (r2 == 0 && r1 == t)){
						return i;
					}
					if(r1 == 0 || r1 == t){
						root[r2] = r1;
					}else{
						root[r1] = r2;
					}
				}
			}
		}
		return -1;
	}

	private int find(int r, int[] root){
		if(root[r] != r){
			root[r] = find(root[r], root);
		}
		return root[r];
	}
}
