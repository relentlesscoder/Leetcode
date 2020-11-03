package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 07/19/2017.
 * #0317 https://leetcode.com/problems/shortest-distance-from-all-buildings/
 */
public class ShortestDistanceFromAllBuildings {

	private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};

	// time O(m^2*n^2), space O(m*n)
	public int shortestDistance(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0){
			return -1;
		}
		int m = grid.length, n = grid[0].length, houseCount = 0, res = Integer.MAX_VALUE;
		int[][] distance = new int[m][n], reachCount = new int[m][n];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == 1){
					houseCount++;
				}
			}
		}
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == 1){
					if(!bfs(i, j, houseCount, grid, distance, reachCount)){
						return -1;
					}
				}
			}
		}
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == 0 && reachCount[i][j] == houseCount){
					res = Math.min(res, distance[i][j]);
				}
			}
		}
		return res == Integer.MAX_VALUE ? -1 : res;
	}

	private boolean bfs(int i, int j, int houseCount, int[][] grid, int[][] distance, int[][] reachCount){
		int count = 1, dist = 0, m = grid.length, n = grid[0].length;
		boolean[][] visited = new boolean[m][n];
		LinkedList<int[]> queue = new LinkedList<>();
		queue.offerLast(new int[]{i, j});
		visited[i][j] = true;
		while(!queue.isEmpty()){
			int size = queue.size();
			dist++;
			while(size-- > 0){
				int[] cur = queue.pollFirst();
				for(int k = 0; k < 4; k++){
					int x = cur[0] + DIRS[k], y = cur[1] + DIRS[k + 1];
					if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]){
						if(grid[x][y] == 0){
							distance[x][y] += dist;
							reachCount[x][y]++;
							visited[x][y] = true;
							queue.offerLast(new int[]{x, y});
						}else if(grid[x][y] == 1){
							count++;
							visited[x][y] = true;
						}
					}
				}
			}
		}
		return count == houseCount;
	}
}
