package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 09/25/2023.
 * #1926 https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/
 */
public class NearestExitFromEntranceInMaze {

	// time O(m * n), space O(m * n)
	public int nearestExit(char[][] maze, int[] entrance) {
		int[] dirs = new int[] {0, -1, 0, 1, 0};
		int steps = 0, m = maze.length, n = maze[0].length;
		LinkedList<int[]> queue = new LinkedList<>();
		queue.offer(entrance);
		maze[entrance[0]][entrance[1]] = '-'; // '-' means visited
		while (!queue.isEmpty()) {
			int count = queue.size();
			while (count-- > 0) {
				int[] curr = queue.poll();
				int r = curr[0], c = curr[1];
				if ((r == 0 || r == m - 1 || c == 0 || c == n - 1) && (r != entrance[0] || c != entrance[1])) { // need to filter out the special case that entrance is on the border
					return steps;
				}
				for (int i = 0; i < 4; i++) {
					int x = r + dirs[i], y = c + dirs[i + 1];
					if (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == '.') {
						maze[x][y] = '-';
						queue.offer(new int[] {x, y});
					}
				}
			}
			steps++;
		}
		return -1;
	}
}
