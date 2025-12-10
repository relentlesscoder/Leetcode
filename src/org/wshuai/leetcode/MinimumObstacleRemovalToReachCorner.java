package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/01/2023.
 * #2290 https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/
 */
public class MinimumObstacleRemovalToReachCorner {

	private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

	// time O(m * n), space O(m * n)
	public int minimumObstacles(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[][] dis = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dis[i][j] = Integer.MAX_VALUE;
			}
		}
		dis[0][0] = 0;
		Deque<int[]> queue = new ArrayDeque<>();
		queue.offerFirst(new int[]{0, 0});
		while (!queue.isEmpty()) {
			int[] curr = queue.pollFirst();
			int r = curr[0], c = curr[1];
			for (int i = 0; i < 4; i++) {
				int x = r + DIRS[i], y = c + DIRS[i + 1];
				if (x >= 0 && x < m && y >= 0 && y < n) {
					int cost = grid[x][y];
					if (dis[x][y] > dis[r][c] + cost) {
						dis[x][y] = dis[r][c] + cost;
						if (grid[x][y] == 1) {
							queue.offerLast(new int[]{x, y});
						} else {
							queue.offerFirst(new int[]{x, y});
						}
					}
				}
			}
		}
		return dis[m - 1][n - 1];
	}

	// time O(m * n), space O(m * n)
	public int minimumObstaclesDijkstra(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[][] dis = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dis[i][j] = Integer.MAX_VALUE;
			}
		}
		dis[0][0] = 0;
		Deque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{0, 0, 0});
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0], c = curr[1];
			for (int i = 0; i < 4; i++) {
				int x = r + DIRS[i], y = c + DIRS[i + 1];
				if (x >= 0 && x < m && y >= 0 && y < n) {
					int cost = grid[x][y];
					if (dis[x][y] > dis[r][c] + cost) {
						dis[x][y] = dis[r][c] + cost;
						queue.offer(new int[]{x, y, dis[x][y]});
					}
				}
			}
		}
		return dis[m - 1][n - 1];
	}
}
