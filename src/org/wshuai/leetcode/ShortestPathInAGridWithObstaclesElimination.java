package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 12/18/2019.
 * #1293 https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
 */
public class ShortestPathInAGridWithObstaclesElimination {

	private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

	// time O(m * n), space O(min(m, n) * k)
	public int shortestPath(int[][] grid, int k) {
		int m = grid.length, n = grid[0].length, shortest = 0;
		Deque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0, 0, k});
		Set<Integer> visited = new HashSet<>();
		visited.add(encode(0, 0, k));
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int[] curr = queue.poll();
				if (curr[0] == m - 1 && curr[1] == n - 1) {
					return shortest;
				}
				int r = curr[0], c = curr[1];
				for (int i = 0; i < 4; i++) {
					int x = r + DIRS[i], y = c + DIRS[i + 1];
					if (x >= 0 && x < m && y >= 0 && y < n) {
						int cnt = curr[2];
						if (grid[x][y] == 1) {
							cnt--;
						}
						int state = encode(x, y, cnt);
						if (cnt >= 0 && !visited.contains(state)) {
							queue.offer(new int[]{x, y, cnt});
							visited.add(encode(x, y, cnt));
						}
					}
				}
			}
			shortest++;
		}
		return -1;
	}

	private int encode(int x, int y, int cnt) {
		//状态压缩
		int res = (cnt << 8) + x;
		res = (res << 8) + y;
		return res;
	}
}
