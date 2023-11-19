package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/30/2023.
 * #2258 https://leetcode.com/problems/escape-the-spreading-fire/
 */
public class EscapeTheSpreadingFire {

	private int[] dirs = new int[]{0, -1, 0, 1, 0};

	// time O(m * n * log(high - low)), space O(m * n)
	public int maximumMinutes(int[][] grid) {
		int res = 0, mins = 0, m = grid.length, n = grid[0].length;
		Deque<int[]> fireQueue = new ArrayDeque<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					grid[i][j] = -1;
					fireQueue.offer(new int[]{i, j});
				}
				if (grid[i][j] == 2) {
					grid[i][j] = 5_000;
				}
			}
		}
		if (fireQueue.isEmpty()) { // Edge case: no fires found in the grid. In this case if we can escape then we can wait as long as possible (return 10^9), otherwise it is impossible (return -1).
			return canEscape(grid, 0, 0, m, n, 0) ? (int) 1e9 : -1;
		}
		while (!fireQueue.isEmpty()) {
			int size = fireQueue.size();
			while (size-- > 0) {
				int[] curr = fireQueue.poll();
				for (int k = 0; k < 4; k++) {
					int x = curr[0] + dirs[k], y = curr[1] + dirs[k + 1];
					if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0) {
						grid[x][y] = grid[curr[0]][curr[1]] - 1;
						fireQueue.offer(new int[]{x, y});
					}
				}
			}
			mins++;
		}
		// Edge case: all fires are blocked by walls. In this case if we can escape then we can wait as long as possible (return 10^9), otherwise it is impossible (return -1).
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0) {
					return canEscape(grid, 0, 0, m, n, 0) ? (int) 1e9 : -1;
				}
			}
		}
		int low = 0, high = mins - 1;
		while (low < high) {
			int mid = (low + high + 1) >> 1;
			if (canEscape(grid, 0, 0, m, n, mid)) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		if (low == 0 && !canEscape(grid, 0, 0, m, n, 0)) { // Edge case: we need to verify if it is possible for waiting 0 minutes
			return -1;
		}
		return low;
	}

	private boolean canEscape(int[][] grid, int i, int j, int m, int n, int threshold) {
		Deque<int[]> pathQueue = new ArrayDeque<>();
		pathQueue.offer(new int[]{0, 0, threshold + 1});
		Set<Integer> visited = new HashSet<>();
		visited.add(0);
		while (!pathQueue.isEmpty()) {
			int size = pathQueue.size();
			while (size-- > 0) {
				int[] curr = pathQueue.poll();
				for (int k = 0; k < 4; k++) {
					int x = curr[0] + dirs[k], y = curr[1] + dirs[k + 1];
					if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 5_000 || visited.contains(x * n + y)) {
						continue;
					}
					int delta = curr[2] + 1 + grid[x][y];
					if ((delta <= 0 || grid[x][y] == 0) && x == m - 1 && y == n - 1) {
						return true;
					}
					if (delta < 0 || grid[x][y] == 0) {
						visited.add(x * n + y);
						pathQueue.offer(new int[]{x, y, curr[2] + 1});
					}
				}
			}
		}
		return false;
	}
}
