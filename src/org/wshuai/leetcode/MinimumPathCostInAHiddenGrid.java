package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 09/28/2023.
 * #1810 https://leetcode.com/problems/minimum-path-cost-in-a-hidden-grid/
 */
public class MinimumPathCostInAHiddenGrid {

	private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	private static final char[] MOVS = new char[]{'U', 'D', 'L', 'R'};
	private static final char[] REV = new char[]{'D', 'U', 'R', 'L'};

	public int findShortestPath(GridMaster master) {
		int[][] grid = new int[200][200];
		for (int i = 0; i < 200; i++) {
			Arrays.fill(grid[i], -1);
		}
		grid[99][99] = 0;
		int[] target = new int[]{-1, -1};
		buildGrid(grid, 99, 99, master, target); //dfs to build the graph
		return getMinCost(grid, new int[]{99, 99}, target); // bfs + Dijkstra to calculate the min cost path
	}

	private int getMinCost(int[][] grid, int[] start, int[] target) {
		int m = grid.length, n = grid[0].length;
		int[][] visited = new int[m][n];
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		queue.offer(new int[]{0, start[0], start[1]});
		visited[start[0]][start[1]] = 1;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			if (curr[1] == target[0] && curr[2] == target[1]) {
				return curr[0];
			}
			for (int i = 0; i < 4; i++) {
				int x = curr[1] + DIRS[i][0], y = curr[2] + DIRS[i][1];
				if (x < 0 || x >= 200 || y < 0 || y >= 200 || grid[x][y] == -1 || visited[x][y] == 1) {
					continue;
				}
				visited[x][y] = 1;
				queue.offer(new int[]{curr[0] + grid[x][y], x, y});
			}
		}
		return -1;
	}

	private void buildGrid(int[][] grid, int r, int c, GridMaster master, int[] target) {
		if (master.isTarget()) {
			target[0] = r;
			target[1] = c;
		}
		for (int i = 0; i < 4; i++) {
			char mov = MOVS[i];
			int[] dir = DIRS[i];
			int x = r + DIRS[i][0], y = c + DIRS[i][1];
			if (master.canMove(mov) && grid[x][y] == -1) {
				int cost = master.move(mov);
				grid[x][y] = cost;
				buildGrid(grid, x, y, master, target);
				master.move(REV[i]);
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
