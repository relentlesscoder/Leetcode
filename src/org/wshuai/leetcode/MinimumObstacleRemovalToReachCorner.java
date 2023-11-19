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

	private int[] dirs = new int[]{0, -1, 0, 1, 0};

	// time O(m * n * log(m * n)), space O(m * n)
	public int minimumObstacles(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		int[][] costs = new int[m][n];
		for (int i = 0; i < m; i++) {
			Arrays.fill(costs[i], Integer.MAX_VALUE);
		}
		costs[0][0] = grid[0][0];
		minQueue.offer(new int[]{0, 0, grid[0][0]});
		while (!minQueue.isEmpty()) {
			int[] curr = minQueue.poll();
			if (curr[0] == m - 1 && curr[1] == n - 1) {
				return curr[2];
			}
			for (int i = 0; i < 4; i++) {
				int x = curr[0] + dirs[i], y = curr[1] + dirs[i + 1];
				if (x >= 0 && x < m && y >= 0 && y < n && costs[x][y] > curr[2] + grid[x][y]) {
					costs[x][y] = curr[2] + grid[x][y];
					minQueue.offer(new int[]{x, y, costs[x][y]});
				}
			}
		}
		return costs[m - 1][n - 1];
	}

	// time O(m * n), space O(m * n)
	public int minimumObstaclesBFS(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		Deque<int[]> queue = new ArrayDeque<>();
		int[][] costs = new int[m][n];
		for (int i = 0; i < m; i++) {
			Arrays.fill(costs[i], Integer.MAX_VALUE);
		}
		costs[0][0] = grid[0][0];
		queue.offer(new int[] {0, 0, grid[0][0]});
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			for (int i = 0; i < 4; i++) {
				int x = curr[0] + dirs[i], y = curr[1] + dirs[i + 1];
				if (x >= 0 && x < m && y >= 0 && y < n && costs[x][y] == Integer.MAX_VALUE) {
					if (grid[x][y] == 1) { // if grid[x][y] is an obstacle, add it to the end of the queue
						costs[x][y] = curr[2] + 1;
						queue.offer(new int[]{x, y, costs[x][y]});
					} else { // if grid[x][y] is an obstacle, add it to the front of the queue since the cost is not increased
						costs[x][y] = curr[2];
						queue.offerFirst(new int[]{x, y, costs[x][y]});
					}
				}
			}
		}
		return costs[m - 1][n - 1];
	}
}
