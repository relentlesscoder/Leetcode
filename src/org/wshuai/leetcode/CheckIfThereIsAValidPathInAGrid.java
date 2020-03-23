package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 03/23/2020.
 * #1391 https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/
 */
public class CheckIfThereIsAValidPathInAGrid {
	// time O(m*n), space O(m*n)
	public boolean hasValidPath(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		LinkedList<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[m][n];
		queue.offerLast(new int[]{0, 0, grid[0][0]});
		visited[0][0] = true;
		while(!queue.isEmpty()){
			int[] cur = queue.pollFirst();
			if(cur[0] == m - 1 && cur[1] == n - 1){
				return true;
			}
			int i = cur[0], j = cur[1], v = cur[2];
			// left
			if(v == 1 || v == 3 || v == 5){
				int x = i, y = j - 1;
				if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]){
					int z = grid[x][y];
					if(z == 1 || z == 4 || z == 6){
						visited[x][y] = true;
						queue.offerLast(new int[]{x, y, z});
					}
				}
			}
			// right
			if(v == 1 || v == 4 || v == 6){
				int x = i, y = j + 1;
				if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]){
					int z = grid[x][y];
					if(z == 1 || z == 3 || z == 5){
						visited[x][y] = true;
						queue.offerLast(new int[]{x, y, z});
					}
				}
			}
			// upper
			if(v == 2 || v == 5 || v == 6){
				int x = i - 1, y = j;
				if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]){
					int z = grid[x][y];
					if(z == 2 || z == 3 || z == 4){
						visited[x][y] = true;
						queue.offerLast(new int[]{x, y, z});
					}
				}
			}
			//lower
			if(v == 2 || v == 3 || v == 4){
				int x = i + 1, y = j;
				if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]){
					int z = grid[x][y];
					if(z == 2 || z == 5 || z == 6){
						visited[x][y] = true;
						queue.offerLast(new int[]{x, y, z});
					}
				}
			}
		}
		return false;
	}
}
