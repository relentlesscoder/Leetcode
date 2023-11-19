package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 02/27/2021.
 * #1730 https://leetcode.com/problems/shortest-path-to-get-food/
 */
public class ShortestPathToGetFood {

	private static final int[] DIRECTIONS = new int[]{ 0, -1, 0, 1, 0 };

	// time O(m*n), space O(m*n)
	public int getFood(char[][] grid) {
		int m = grid.length, n = grid[0].length, length = 0;
		LinkedList<int[]> queue = new LinkedList<>();
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == '*'){
					queue.offerLast(new int[]{i, j});
					grid[i][j] = 'X';
					break;
				}
			}
		}
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int[] cur = queue.pollFirst();
				for(int d = 0; d < 4; d++){
					int x = cur[0] + DIRECTIONS[d];
					int y = cur[1] + DIRECTIONS[d + 1];
					if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 'X'){
						continue;
					}
					if(grid[x][y] == '#'){
						return length + 1;
					}
					queue.offerLast(new int[]{x, y});
					grid[x][y] = 'X';
				}
			}
			length++;
		}
		return -1;
	}
}
