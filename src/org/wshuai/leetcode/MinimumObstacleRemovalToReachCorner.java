package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/01/2023.
 * #2290 https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/
 */
public class MinimumObstacleRemovalToReachCorner {

	private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

	// time O(m * n), space O(m * n)
	public int minimumObstaclesBFSOptimization(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[][] dist = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		dist[0][0] = 0;
		Deque<int[]> queue = new ArrayDeque<>();
		queue.offerFirst(new int[]{0, 0});
		while (!queue.isEmpty()) {
			int[] curr = queue.pollFirst();
			int r = curr[0], c = curr[1];
			for (int i = 0; i < 4; i++) {
				int x = r + DIRS[i], y = c + DIRS[i + 1];
				if (x >= 0 && x < m && y >= 0 && y < n) {
					int cost = grid[x][y];
					if (dist[x][y] > dist[r][c] + cost) {
						dist[x][y] = dist[r][c] + cost;
						if (grid[x][y] == 1) {
							queue.offerLast(new int[]{x, y});
						} else {
							queue.offerFirst(new int[]{x, y});
						}
					}
				}
			}
		}
		return dist[m - 1][n - 1];
	}

	// time O(m * n), space O(m * n)
	public int minimumObstaclesBFS(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[][] dist = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		dist[0][0] = 0;
		Deque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{0, 0});
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0], c = curr[1];
			for (int i = 0; i < 4; i++) {
				int x = r + DIRS[i], y = c + DIRS[i + 1];
				if (x >= 0 && x < m && y >= 0 && y < n) {
					int cost = grid[x][y];
					if (dist[x][y] > dist[r][c] + cost) {
						dist[x][y] = dist[r][c] + cost;
						queue.offer(new int[]{x, y});
					}
				}
			}
		}
		return dist[m - 1][n - 1];
	}

	// time O(m * n * log(m * n)), space O(m * n)
	public int minimumObstaclesDijkstra(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		boolean[][] visited = new boolean[m][n];
		int[][] dist = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		dist[0][0] = 0;
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		queue.offer(new int[]{0, 0, 0});
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0], c = curr[1];
			if (visited[r][c]) {
				continue;
			}
			visited[r][c] = true;
			for (int i = 0; i < 4; i++) {
				int x = r + DIRS[i], y = c + DIRS[i + 1];
				if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
					int cost = grid[x][y];
					if (dist[x][y] > dist[r][c] + cost) {
						dist[x][y] = dist[r][c] + cost;
						queue.offer(new int[]{x, y, dist[x][y]});
					}
				}
			}
		}
		return dist[m - 1][n - 1];
	}
}
