package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/25/2020.
 * #1631 https://leetcode.com/problems/path-with-minimum-effort/
 */
public class PathWithMinimumEffort {

	private static final int[] dir = new int[]{0, -1, 0, 1, 0};

	// time O(m*n*log(m*n)), space O(m*n)
	public int minimumEffortPath(int[][] heights) {
		int m = heights.length, n = heights[0].length;
		int[][] effort = new int[m][n];
		for (int i = 0; i < m; i++) {
			Arrays.fill(effort[i], Integer.MAX_VALUE);
		}
		effort[0][0] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		pq.offer(new int[]{0, 0, 0});
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (cur[1] == m - 1 && cur[2] == n - 1) {
				return cur[0];
			}
			for (int i = 0; i < 4; i++) {
				int x = cur[1] + dir[i], y = cur[2] + dir[i + 1];
				if (x >= 0 && x < m && y >= 0 && y < n) {
					int next = Math.max(cur[0],
							Math.abs(heights[x][y] - heights[cur[1]][cur[2]]));
					if (next < effort[x][y]) {
						effort[x][y] = next;
						pq.offer(new int[]{next, x, y});
					}
				}
			}
		}
		return -1;
	}
}
