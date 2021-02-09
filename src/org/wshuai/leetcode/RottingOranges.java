package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 09/06/2019.
 * #0994 https://leetcode.com/problems/rotting-oranges/
 */
public class RottingOranges {

	private static final int[] DIRECTIONS = new int[]{ 0, -1, 0, 1, 0 };

	// time O(m*n), space O(m*n)
	public int orangesRotting(int[][] grid) {
		int minutes = 0, fresh = 0, m = grid.length, n = grid[0].length;
		LinkedList<int[]> queue = new LinkedList<>();
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == 2){
					queue.offerLast(new int[]{i, j});
				}else if(grid[i][j] == 1){
					fresh++;
				}
			}
		}
		if(fresh == 0){
			return 0;
		}
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int[] cur = queue.pollFirst();
				for(int i = 0; i < 4; i++){
					int x = cur[0] + DIRECTIONS[i], y = cur[1] + DIRECTIONS[i + 1];
					if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1){
						if(--fresh == 0){
							return minutes + 1;
						}
						grid[x][y] = 2;
						queue.offerLast(new int[]{x, y});
					}
				}
			}
			minutes++;
		}
		return -1;
	}
}
