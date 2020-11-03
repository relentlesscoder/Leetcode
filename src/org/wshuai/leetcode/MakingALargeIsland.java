package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 12/20/2019.
 * #0827 https://leetcode.com/problems/making-a-large-island/
 */
public class MakingALargeIsland {

	private static final int VISITED = 5_000;

	private static final int[] DIRS = new int[]{0, 1, 0, -1, 0};

	// time O(m*n), space O(m*n)
	public int largestIsland(int[][] grid) {
		int res = Integer.MIN_VALUE, m = grid.length, n = grid[0].length;
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == 1){
					bfs(i, j, m, n, grid);
				}
			}
		}
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == VISITED){
					continue;
				}
				res = Math.max(res, Math.abs(grid[i][j]));
			}
		}
		return res == Integer.MIN_VALUE ? m*n : res + 1;
	}

	private void bfs(int i, int j, int m, int n, int[][] grid){
		int count = 0;
		Set<Integer> border = new HashSet<>();
		LinkedList<int[]> queue = new LinkedList<>();
		queue.offerLast(new int[]{i, j});
		// 5_000 denotes visited cells
		grid[i][j] = 5_000;
		while(!queue.isEmpty()){
			int[] cur = queue.pollFirst();
			count++;
			for(int k = 0; k < 4; k++){
				int x = cur[0] + DIRS[k], y = cur[1] + DIRS[k + 1];
				if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == VISITED){
					continue;
				}
				// board cell
				if(grid[x][y] <= 0){
					border.add(x * n + y);
				}
				// unvisited cell
				if(grid[x][y] == 1){
					grid[x][y] = VISITED;
					queue.offerLast(new int[]{x, y});
				}
			}
		}
		// assign the board the size (negative)
		// of the current island for future merging.
		for(int b : border){
			grid[b / n][b % n] -= count;
		}
	}
}
