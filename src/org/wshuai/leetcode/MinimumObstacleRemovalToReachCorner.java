package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/01/2023.
 * #2290 https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/
 */
public class MinimumObstacleRemovalToReachCorner {

	private int[] dirs = new int[] {0, -1, 0, 1, 0};

	// time O(m * n * log(m * n)), space O(m * n)
	public int minimumObstacles(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[][] dist = new int[m][n];
		for (int[] d : dist) {
			Arrays.fill(d, Integer.MAX_VALUE);
		}
		PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		minQueue.offer(new int[]{0, 0, grid[0][0]});
		while (!minQueue.isEmpty()) {
			int[] curr = minQueue.poll();
			if (curr[0] == m - 1 && curr[1] == n - 1) {
				return curr[2];
			}
			for (int k = 0; k < 4; k++) {
				int x = curr[0] + dirs[k], y = curr[1] + dirs[k + 1];
				if (x >= 0 && x < m && y >= 0 && y < n && curr[2] + grid[x][y] < dist[x][y]) {
					dist[x][y] = curr[2] + grid[x][y];
					minQueue.offer(new int[]{x, y, dist[x][y]});
				}
			}
		}
		return dist[m - 1][n - 1];
	}

	// time O(m * n), space O(m * n)
	public int minimumObstaclesBFS(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[][] dist = new int[m][n];
		for (int[] d : dist) {
			Arrays.fill(d, Integer.MAX_VALUE);
		}
		Deque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{0, 0, grid[0][0]});
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			for (int k = 0; k < 4; k++) {
				int x = curr[0] + dirs[k], y = curr[1] + dirs[k + 1];
				if (x >= 0 && x < m && y >= 0 && y < n && dist[x][y] == Integer.MAX_VALUE) {
					int d = curr[2] + grid[x][y];
					dist[x][y] = d;
					if (grid[x][y] == 0) {
						queue.offerFirst(new int[]{x, y, d});
					} else {
						queue.offer(new int[]{x, y, d});
					}
				}
			}
		}
		return dist[m - 1][n - 1];
	}
}
