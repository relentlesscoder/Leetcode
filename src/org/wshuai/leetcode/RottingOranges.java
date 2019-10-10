package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 9/6/19.
 * #994 https://leetcode.com/problems/rotting-oranges/
 */
public class RottingOranges {
	public int orangesRotting(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;
		int min = 0;
		int count = 0;
		int[][] aux = new int[2][4];
		aux[0] = new int[]{-1, 1, 0, 0};
		aux[1] = new int[]{0, 0, -1, 1};
		LinkedList<int[]> queue = new LinkedList<>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				// record number of oranges (fresh and rotten)
				if (grid[i][j] != 0) {
					count++;
				}
				if (grid[i][j] == 2) {
					int[] cell = new int[2];
					cell[0] = i;
					cell[1] = j;
					queue.offer(cell);
				}
			}
		}
		int real = 0;
		LinkedList<int[]> next = new LinkedList<>();
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			grid[curr[0]][curr[1]] = -1;
			// record final number of rotten oranges
			real++;
			for (int k = 0; k < 4; k++) {
				int x = curr[0] + aux[0][k];
				int y = curr[1] + aux[1][k];
				if (x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == 1) {
					grid[x][y] = 2;
					int[] cell = new int[2];
					cell[0] = x;
					cell[1] = y;
					next.offer(cell);
				}
			}
			if (queue.isEmpty() && !next.isEmpty()) {
				queue = next;
				next = new LinkedList<>();
				min++;
			}
		}
		return count == real ? min : -1;
	}
}
