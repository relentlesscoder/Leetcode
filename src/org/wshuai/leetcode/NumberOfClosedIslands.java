package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 11/13/19.
 * #1254 https://leetcode.com/problems/number-of-closed-islands/
 */
public class NumberOfClosedIslands {
	public int closedIsland(int[][] grid) {
		int[][] move = new int[][]{
				{1, -1, 0, 0},
				{0, 0, 1, -1}
		};
		int res = 0;
		int r = grid.length;
		int c = grid[0].length;
		LinkedList<int[]> queue = new LinkedList<>();
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(grid[i][j] == 0){
					boolean closed = true;
					queue.offerLast(new int[]{i, j});
					grid[i][j] = 1;
					while(!queue.isEmpty()){
						int[] p = queue.pollFirst();
						for(int k = 0; k < 4; k++){
							int x = p[0] + move[0][k];
							int y = p[1] + move[1][k];
							if(closed && (x < 0 || y < 0 || x >= r || y >= c)){
								closed = false;
							}
							if(x >= 0 && y >= 0 && x < r && y < c && grid[x][y] == 0){
								grid[x][y] = 1;
								queue.offerLast(new int[]{x, y});
							}
						}
					}
					res += closed ? 1 : 0;
				}
			}
		}
		return res;
	}
}
