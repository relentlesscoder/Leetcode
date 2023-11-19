package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 03/01/2020.
 * #1368 https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/
 */
public class MinimumCostToMakeAtLeastOneValidPathInAGrid {

	// time O(m*n*log(m*n)), space O(m*n)
	// Dijkstra's algorithm
	private static final int[][] dirs = new int[][]{
			{0, 0, 1, -1},
			{1, -1, 0, 0}
	};

	public int minCost(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		pq.offer(new int[]{0, 0, 0});
		int[][] cost = new int[m][n];
		for (int i = 0; i < m; i++) {
			Arrays.fill(cost[i], Integer.MAX_VALUE);
		}
		cost[0][0] = 0;
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int costToCur = cur[0], i = cur[1], j = cur[2];
			for (int k = 0; k < 4; k++) {
				int x = i + dirs[0][k], y = j + dirs[1][k];
				if (x >= 0 && x < m && y >= 0 && y < n) {
					int costToMove = costToCur;
					if (k + 1 != grid[i][j]) {
						costToMove++;
					}
					if (cost[x][y] > costToMove) {
						cost[x][y] = costToMove;
						pq.offer(new int[]{costToMove, x, y});
					}
				}
			}
		}
		return cost[m - 1][n - 1];
	}
}
