package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 04/04/2017.
 * #0490 https://leetcode.com/problems/the-maze/
 */
public class TheMaze {

	private static final int[] dirs = new int[]{0, 1, 0, -1, 0};

	// time O(m*n)
	public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		int m = maze.length, n = maze[0].length;
		return dfs(start, destination, maze, m, n, new boolean[m][n]);
	}

	private boolean dfs(int[] start, int[] dest, int[][] maze, int m, int n, boolean[][] visited) {
		if (start[0] == dest[0] && start[1] == dest[1]) {
			return true;
		}
		if (visited[start[0]][start[1]]) {
			return false;
		}
		visited[start[0]][start[1]] = true;
		for (int i = 0; i < 4; i++) {
			int x = start[0], y = start[1];
			while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
				x += dirs[i];
				y += dirs[i + 1];
			}
			x -= dirs[i];
			y -= dirs[i + 1];
			if (!visited[x][y] && dfs(new int[]{x, y}, dest, maze, m, n, visited)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasPathBFS(int[][] maze, int[] start, int[] destination) {
		int m = maze.length, n = maze[0].length;
		LinkedList<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[m][n];
		queue.offerLast(start);
		visited[start[0]][start[1]] = true;
		while (!queue.isEmpty()) {
			int[] cur = queue.pollFirst();
			for (int i = 0; i < 4; i++) {
				int x = cur[0], y = cur[1];
				while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
					x += dirs[i];
					y += dirs[i + 1];
				}
				x -= dirs[i];
				y -= dirs[i + 1];
				if (x == destination[0] && y == destination[1]) {
					return true;
				}
				if (!visited[x][y]) {
					queue.offerLast(new int[]{x, y});
					visited[x][y] = true;
				}
			}
		}
		return false;
	}
}
