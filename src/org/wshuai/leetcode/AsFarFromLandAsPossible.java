package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/26/2019.
 * #1162 https://leetcode.com/problems/as-far-from-land-as-possible/
 */
public class AsFarFromLandAsPossible {
	private static final int[] DIRS = new int[] {-1, 0, 1, 0, -1};

	// time O(m * n), space O(min(m, n))
	public int maxDistance(int[][] grid) {
		// 从所有的陆地格子出发进行BFS，BFS所能走的最远步数就是海洋格子与陆地格子
		// 的最远距离。
		int distance = 0, n = grid.length;
		Deque<int[]> queue = new ArrayDeque<>();
		// 遍历数组，将所有的陆地格子加入FIFO队列
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					queue.offer(new int[] {i, j});
					grid[i][j] = -1;
				}
			}
		}
		// BFS向所有的海洋格子前进
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int[] curr = queue.poll();
				for (int i = 0; i < 4; i++) {
					int x = curr[0] + DIRS[i], y = curr[1] + DIRS[i + 1];
					if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
						queue.offer(new int[] {x, y});
						grid[x][y] = -1;
					}
				}
			}
			distance++;
		}
		// 注意由于BFS的第一步还是走在陆地上，所以应该要减1
		return distance == 1 ? -1 : distance - 1;
	}
}
