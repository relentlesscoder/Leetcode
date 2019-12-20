package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 12/20/2019.
 * #827 https://leetcode.com/problems/making-a-large-island/
 */
public class MakingALargeIsland {
	private static final int[][] dirs = new int[][]{
			{1, -1, 0, 0},
			{0, 0, 1, -1}
	};

	public int largestIsland(int[][] grid) {
		int r = grid.length;
		int c = grid[0].length;
		int[][] count = new int[r][c];
		LinkedList<int[]> queue = new LinkedList<>();
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(grid[i][j] == 1){
					int island = 0;
					Set<Integer> border = new HashSet<>();
					queue = new LinkedList<>();
					queue.offerLast(new int[]{i, j});
					grid[i][j] = -1;
					while(!queue.isEmpty()){
						int[] cur = queue.pollFirst();
						island++;
						for(int k = 0; k < 4; k++){
							int x = cur[0] + dirs[0][k];
							int y = cur[1] + dirs[1][k];
							if(x >= 0 && x < r && y >= 0 && y < c && grid[x][y] != -1){
								if(grid[x][y] == 0){
									border.add(x * c + y);
								}
								if(grid[x][y] == 1){
									grid[x][y] = -1;
									queue.offerLast(new int[]{x, y});
								}
							}
						}
					}
					for(int cell : border){
						count[cell / c][cell % c] += island;
					}
				}
			}
		}
		int maxIsland = 0;
		int zeros = 0;
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(grid[i][j] == 0){
					zeros++;
					maxIsland = Math.max(count[i][j] + 1, maxIsland);
				}
			}
		}
		return zeros > 0 ? maxIsland : r * c;
	}
}
