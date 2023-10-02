package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/01/2023.
 * #2577 https://leetcode.com/problems/minimum-time-to-visit-a-cell-in-a-grid/
 */
public class MinimumTimeToVisitACellInAGrid {

	private int[] dirs = new int[] {0, -1, 0, 1, 0};

	// time O(m * n * log(m * n)), space O(m * n)
	public int minimumTimeDijkstra(int[][] grid) {
		if (grid[0][1] > 1 && grid[1][0] > 1) { // base case
			return -1;
		}
		int m = grid.length, n = grid[0].length;
		PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		minQueue.offer(new int[]{0, 0, grid[0][0]});
		int[][] visited = new int[m][n];
		for (int i = 0; i < m; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		while(!minQueue.isEmpty()) {
			int[] curr = minQueue.poll();
			if (curr[0] == m - 1 && curr[1] == n - 1) {
				break;
			}
			for (int i = 0; i < 4; i++) {
				int x = curr[0] + dirs[i], y = curr[1] + dirs[i + 1];
				if (x < 0 || x >= m || y < 0 || y >= n) {
					continue;
				}
				int wait = ((grid[x][y] - curr[2]) % 2 == 0) ? 1 : 0;
				int time = Math.max(curr[2] + 1, grid[x][y] + wait);
				if (time < visited[x][y]) {
					visited[x][y] = time;
					minQueue.offer(new int[]{x, y, time});
				}
			}
		}
		return visited[m - 1][n - 1];
	}

	// time O(m * n * log(m * n)), space O(m * n)
	public int minimumTime(int[][] grid) {
		if (grid[0][1] > 1 && grid[1][0] > 1) {
			return -1;
		}
		int m = grid.length, n = grid[0].length;
		PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		minQueue.offer(new int[]{0, 0, grid[0][0]});
		while(!minQueue.isEmpty()) {
			int[] curr = minQueue.poll();
			if (curr[0] == m - 1 && curr[1] == n - 1) {
				return curr[2];
			}
			if (grid[curr[0]][curr[1]] == -1) {
				continue;
			}
			grid[curr[0]][curr[1]] = -1;
			for (int i = 0; i < 4; i++) {
				int x = curr[0] + dirs[i], y = curr[1] + dirs[i + 1];
				if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == -1) {
					continue;
				}
				// If next is greater than the current, we need bounce back and forth to wait for the time
				// For example, current time is 2 and next cell is 5 (delta is odd), we need to go by the sequence "previous -> curr -> next" thus the time we reach next is 5;
				// If current time is 3 and next cell is 5 (delta is even), we need to go by the sequence "previous -> curr -> next" thus the time we reach next is 5 + 1.
				int wait = ((grid[x][y] - curr[2]) % 2 == 0) ? 1 : 0;
				minQueue.offer(new int[]{x, y, Math.max(grid[x][y] + wait, curr[2] + 1)});
			}
		}
		return -1;
	}
}
