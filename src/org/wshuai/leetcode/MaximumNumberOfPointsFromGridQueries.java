package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/01/2023.
 * #2503 https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/
 */
public class MaximumNumberOfPointsFromGridQueries {

	private int[] dirs = new int[] {0, -1, 0, 1, 0};

	// time O(k * log(m * n)), space O(k + m * n)
	public int[] maxPoints(int[][] grid, int[] queries) {
		int sum = 0, m = grid.length, n = grid[0].length, k = queries.length;
		Integer[] sortedIndexes = new Integer[k];
		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			sortedIndexes[i] = i;
		}
		Arrays.sort(sortedIndexes, (a, b) -> queries[a] - queries[b]);
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		queue.offer(new int[] {0, 0, grid[0][0]});
		grid[0][0] = 0;
		for (int i = 0; i < k; i++) {
			int index = sortedIndexes[i], query = queries[index];
			while (!queue.isEmpty() && queue.peek()[2] < query) {
				int[] curr = queue.poll();
				sum += 1;
				for (int d = 0; d < 4; d++) {
					int x = curr[0] + dirs[d], y = curr[1] + dirs[d + 1];
					if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
						continue;
					}
					queue.offer(new int[]{x, y, grid[x][y]});
					grid[x][y] = 0;
				}
			}
			res[index] = sum;
		}
		return res;
	}
}
