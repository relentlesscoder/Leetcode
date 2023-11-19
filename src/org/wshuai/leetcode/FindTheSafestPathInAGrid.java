package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 09/30/2023.
 * #2812 https://leetcode.com/problems/find-the-safest-path-in-a-grid/
 */
public class FindTheSafestPathInAGrid {

	private int[] dirs = new int[]{0, -1, 0, 1, 0};

	// time O(n^2 * log(n^2)), space O(n^2)
	public int maximumSafenessFactor(List<List<Integer>> grid) {
		int n = grid.size();
		int[][] distanceGrid = new int[n][n];
		Deque<int[]> queue = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid.get(i).get(j) == 1) {
					distanceGrid[i][j] = 0;
					queue.offer(new int[] {i, j});
				} else {
					distanceGrid[i][j] = -1;
				}
			}
		}
		buildDistanceGrid(distanceGrid, queue, n);
		return findMaximumSafenessFactor(distanceGrid, 0, 0, n);
	}

	// time O(n^2 * log(n^2)), space O(n^2)
	private int findMaximumSafenessFactor(int[][] grid, int i, int j, int n) {
		PriorityQueue<int[]> maxQueue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
		maxQueue.offer(new int[]{grid[i][j], i, j});
		while (!maxQueue.isEmpty()) {
			int[] curr = maxQueue.poll();
			if (curr[1] == n - 1 && curr[2] == n - 1) {
				return curr[0];
			}
			for (int k = 0; k < 4; k++) {
				int r = curr[1] + dirs[k], c = curr[2] + dirs[k + 1];
				if (Math.min(r, c) >= 0 && Math.max(r, c) < n && grid[r][c] != -1) {
					maxQueue.offer(new int[] {Math.min(curr[0], grid[r][c]), r, c});
					grid[r][c] = -1;
				}
			}
		}
		return 0;
	}

	// time O(n^2), space O(n)
	private void buildDistanceGrid(int[][] grid, Deque<int[]> queue, int n) {
		int dist = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int[] curr = queue.poll();
				for (int k = 0; k < 4; k++) {
					int r = curr[0] + dirs[k], c = curr[1] + dirs[k + 1];
					if (Math.min(r, c) >= 0 && Math.max(r, c) < n && grid[r][c] == -1) {
						grid[r][c] = dist;
						queue.offer(new int[] {r, c});
					}
				}
			}
			dist++;
		}
	}
}
