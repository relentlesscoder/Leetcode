package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 11/17/2019.
 * #0778 https://leetcode.com/problems/swim-in-rising-water/
 */
public class SwimInRisingWater {

	private static final int[] dirs = new int[]{0, 1, 0, -1, 0};

	// time O(n^2 * log(n)), space O(n^2)
	public int swimInWater(int[][] grid) {
		int time = 0, n = grid.length;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		pq.offer(new int[]{0, 0, grid[0][0]});
		while (!pq.isEmpty() && grid[n - 1][n - 1] >= 0) {
			// poll the next unexplored cell with the smallest time
			int[] cur = pq.poll();
			time = cur[2];
			// dfs to search the reachable cells (grid[x][y] <= time) and add
			// their unreachable neighbors to the priority queue
			dfs(cur[0], cur[1], n, time, grid, pq);
		}
		return time;
	}

	private void dfs(int i, int j, int n, int time, int[][] grid, PriorityQueue<int[]> pq) {
		// set -1 to mark visited cells
		grid[i][j] = -1;
		for (int k = 0; k < 4; k++) {
			int x = i + dirs[k], y = j + dirs[k + 1];
			if (x < 0 || x >= n || y < 0 || y >= n || grid[x][y] == -1) {
				continue;
			}
			if (grid[x][y] > time) {
				pq.offer(new int[]{x, y, grid[x][y]});
			} else {
				dfs(x, y, n, time, grid, pq);
			}
		}
	}

	public int swimInWaterUnionFind(int[][] grid) {
		int n = grid.length, len = n * n;
		int[] root = new int[len];
		for (int i = 0; i < len; i++) {
			root[i] = i;
		}
		int[][] map = new int[len][3];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[grid[i][j]] = new int[]{i, j, i * n + j};
			}
		}
		for (int time = 0; time < len; time++) {
			int[] cur = map[time];
			for (int k = 0; k < 4; k++) {
				int x = cur[0] + dirs[k], y = cur[1] + dirs[k + 1];
				if (x < 0 || x >= n || y < 0 || y >= n || grid[x][y] > time) {
					continue;
				}
				int[] next = map[grid[x][y]];
				int rootNext = findRoot(next[2], root), rootCur = findRoot(cur[2], root);
				if ((rootCur == 0 && rootNext == len - 1) || (rootCur == len - 1 && rootNext == 0)) {
					return time;
				}
				if (rootCur == 0 || rootCur == len - 1) {
					root[rootNext] = rootCur;
				} else {
					root[rootCur] = rootNext;
				}
			}
		}
		return -1;
	}

	private int findRoot(int r, int[] root) {
		if (r != root[r]) {
			root[r] = findRoot(root[r], root);
		}
		return root[r];
	}
}
