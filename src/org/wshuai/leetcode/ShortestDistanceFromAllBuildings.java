package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Wei on 7/19/17.
 * #317 https://leetcode.com/problems/shortest-distance-from-all-buildings/
 */
public class ShortestDistanceFromAllBuildings {
	public int shortestDistance(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int row = grid.length;
		int col = grid[0].length;
		//save distances to all buildings from the current position
		int[][] aux = new int[row][col];
		//save total buildings can be reached from the current position
		int[][] reach = new int[row][col];
		int[] hm = new int[]{0, 0, -1, 1};
		int[] vm = new int[]{-1, 1, 0, 0};
		//total number of buildings
		int total = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == 1) {
					//for each building, do BFS to find the shortest distance from it to all empty lands
					aux[i][j] = -1;
					boolean[][] visited = new boolean[row][col];
					Queue<int[]> queue = new LinkedList<int[]>();
					Queue<int[]> next = new LinkedList<int[]>();
					int level = 0;
					int[] bu = new int[]{i, j};
					queue.offer(bu);
					visited[i][j] = true;
					while (!queue.isEmpty()) {
						int[] po = queue.poll();
						for (int k = 0; k < 4; k++) {
							int x = po[0] + hm[k];
							int y = po[1] + vm[k];
							if (x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == 0 && !visited[x][y]) {
								aux[x][y] += level + 1;
								reach[x][y]++;
								visited[x][y] = true;
								next.offer(new int[]{x, y});
							}
						}
						if (queue.isEmpty()) {
							queue = next;
							next = new LinkedList<int[]>();
							level++;
						}
					}
					total++;
				} else if (grid[i][j] == 2) {
					aux[i][j] = -1;
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == 0 && reach[i][j] == total && aux[i][j] > 0) {
					min = Math.min(min, aux[i][j]);
				}
			}
		}
		return min == Integer.MAX_VALUE ? -1 : min;
	}
}
