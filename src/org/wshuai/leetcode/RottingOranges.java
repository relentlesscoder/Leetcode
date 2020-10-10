package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 09/06/2019.
 * #0994 https://leetcode.com/problems/rotting-oranges/
 */
public class RottingOranges {

	private static final int[] DIRECTIONS = new int[]{0, 1, 0, -1, 0};

	// time O(m*n), space O(m*n)
	public int orangesRotting(int[][] grid) {
		int m = grid.length, n = grid[0].length, total = 0, minutes = 0, count = 0;
		LinkedList<int[]> queue = new LinkedList<>();
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == 0){
					continue;
				}
				if(grid[i][j] == 2){
					queue.offerLast(new int[]{i, j});
				}
				total++;
			}
		}
		if(total == 0){
			return 0;
		}
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int[] cur = queue.pollFirst();
				if(++count == total){
					return minutes;
				}
				for(int k = 0; k < 4; k++){
					int x = cur[0] + DIRECTIONS[k], y = cur[1] + DIRECTIONS[k + 1];
					if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1){
						queue.offerLast(new int[]{x, y});
						grid[x][y] = 2;
					}
				}
			}
			minutes++;
		}
		return -1;
	}
}
