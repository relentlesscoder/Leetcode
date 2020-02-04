package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 07/19/2017.
 * #0317 https://leetcode.com/problems/shortest-distance-from-all-buildings/
 */
public class ShortestDistanceFromAllBuildings {
	private static final int[] dirs = new int[]{0, 1, 0, -1, 0};

	// time O(m^2*n^2)
	public int shortestDistance(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0){
			return -1;
		}
		int m = grid.length, n = grid[0].length, houseCount = 0, minDistance = Integer.MAX_VALUE;
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
					if(!bfs(grid, distance, reachCount, houseCount, m, n, i, j)){
						return -1;
					}
				}
			}
		}
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == 0 && reachCount[i][j] == houseCount){
					minDistance = Math.min(minDistance, distance[i][j]);
				}
			}
		}
		return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
	}

	private boolean bfs(int[][] grid, int[][] distance, int[][] reachCount, int houseCount, int m, int n, int x, int y){
		boolean[][] visited = new boolean[m][n];
		LinkedList<int[]> queue = new LinkedList<>();
		queue.offerLast(new int[]{x, y});
		int dist = 0, count = 0;
		while(!queue.isEmpty()){
			int size = queue.size();
			dist++;
			while(size-- > 0){
				int[] cur = queue.poll();
				for(int k = 0; k < 4; k++){
					int nx = cur[0] + dirs[k];
					int ny = cur[1] + dirs[k + 1];
					if(nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[nx][ny]){
						if(grid[nx][ny] == 0){
							distance[nx][ny] += dist;
							visited[nx][ny] = true;
							reachCount[nx][ny]++;
							queue.offer(new int[]{nx, ny});
						}else if(grid[nx][ny] == 1){
							count++;
							visited[nx][ny] = true;
						}
					}
				}
			}
		}
		return count == houseCount;
	}
}
