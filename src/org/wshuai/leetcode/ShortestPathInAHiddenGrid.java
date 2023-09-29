package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 09/29/2023.
 * #1778 https://leetcode.com/problems/shortest-path-in-a-hidden-grid/
 */
public class ShortestPathInAHiddenGrid {

	private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	private static final char[][] MOVS = new char[][]{{'U', 'D'}, {'D', 'U'}, {'L', 'R'}, {'R', 'L'}};

	// time O(m * n), space O(1000 * 1000)
	public int findShortestPath(GridMaster master) {
		int[][] grid = new int[1_000][1_000]; // Set to 1000 to avoid negative coordination
		for (int i = 0; i < 1_000; i++) {
			Arrays.fill(grid[i], 0);
		}
		// Set the start at the center of square [0,0], [0,999], [999,999] and [999, 0] so any future moves will not cross the boarder.
		// The possible extreme cases: [499,499] is the upper right, upper left, bottom left and bottom right
		int[] start = new int[]{499, 499}, target = new int[]{-1, -1};
		grid[499][499] = 100;
		buildGrid(grid, 499, 499, target, master); // dfs to explore and build the grid
		return shortestPath(grid, start, target); // bfs (or dfs) to calculate the shortest path
	}

	private int shortestPath(int[][] grid, int[] start, int[] target) {
		int steps = 0;
		Deque<int[]> queue = new ArrayDeque<>();
		queue.offer(start);
		grid[start[0]][start[1]] = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int[] curr = queue.poll();
				if (curr[0] == target[0] && curr[1] == target[1]) {
					return steps;
				}
				for (int i = 0; i < 4; i++) {
					int x = curr[0] + DIRS[i][0], y = curr[1] + DIRS[i][1];
					if (x < 0 || x >= 1_000 || y < 0 || y >= 1_000 || grid[x][y] == 0) {
						continue;
					}
					grid[x][y] = 0;
					queue.offer(new int[]{x, y});
				}
			}
			steps++;
		}
		return -1;
	}

	private void buildGrid(int[][] grid, int i, int j, int[] target, GridMaster master) {
		if (master.isTarget()) {
			target[0] = i;
			target[1] = j;
		}
		for (int k = 0; k < 4; k++) {
			char mov = MOVS[k][0];
			int x = i + DIRS[k][0], y = j + DIRS[k][1];
			if (master.canMove(mov) && grid[x][y] != 100) {
				master.move(mov); // move it
				grid[x][y] = 100;
				buildGrid(grid, x, y, target, master);
				master.move(MOVS[k][1]); // reset the previous move
			}
		}
	}

	/**
	 * // This is the GridMaster's API interface.
	 * // You should not implement it, or speculate about its implementation
	 */
	private interface GridMaster {
		boolean canMove(char direction);

		int move(char direction);

		boolean isTarget();
	}
}
