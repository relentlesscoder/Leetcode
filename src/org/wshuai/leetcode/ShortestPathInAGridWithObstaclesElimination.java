package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 12/18/2019.
 * #1293 https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
 */
public class ShortestPathInAGridWithObstaclesElimination {

	private static final int[] DIRECTIONS = new int[]{ 0, -1, 0, 1, 0 };

	// time O(m*n*k), space O(m*n*k)
	public int shortestPath(int[][] grid, int k) {
		int steps = 0, m = grid.length, n = grid[0].length;
		boolean[][][] visited = new boolean[m][n][k + 1];
		LinkedList<int[]> queue = new LinkedList<>();
		queue.offerLast(new int[]{0, 0, 0});
		visited[0][0][0] = true;
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int[] cur = queue.pollFirst();
				if(cur[0] == m - 1 && cur[1] == n - 1){
					return steps;
				}
				for(int d = 0; d < 4; d++){
					int x = cur[0] + DIRECTIONS[d], y = cur[1] + DIRECTIONS[d + 1],
						cnt = cur[2];
					if(x < 0 || x >= m || y < 0 || y >= n){
						continue;
					}
					if(grid[x][y] == 1){
						cnt++;
					}
					if(cnt <= k && !visited[x][y][cnt]){
						visited[x][y][cnt] = true;
						queue.offerLast(new int[]{x, y, cnt});
					}
				}
			}
			steps++;
		}
		return -1;
	}
}
