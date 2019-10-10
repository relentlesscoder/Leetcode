package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 8/22/19.
 * #1030 https://leetcode.com/problems/matrix-cells-in-distance-order/
 */
public class MatrixCellsInDistanceOrder {

	public int[][] allCellsDistOrderBFS(int R, int C, int r0, int c0) {
		boolean[][] visited = new boolean[R][C];
		int[][] result = new int[R * C][2];
		int i = 0;
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[]{r0, c0});
		while (!queue.isEmpty()) {
			int[] cell = queue.poll();
			int r = cell[0];
			int c = cell[1];

			if (r < 0 || r >= R || c < 0 || c >= C) {
				continue;
			}
			if (visited[r][c]) {
				continue;
			}

			result[i] = cell;
			i++;
			visited[r][c] = true;

			queue.offer(new int[]{r, c - 1});
			queue.offer(new int[]{r, c + 1});
			queue.offer(new int[]{r - 1, c});
			queue.offer(new int[]{r + 1, c});
		}
		return result;
	}

	public int[][] allCellsDistOrderSorting(int R, int C, int r0, int c0) {
		List<int[]> cells = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int[] arr = new int[2];
				arr[0] = i;
				arr[1] = j;
				cells.add(arr);
			}
		}
		Collections.sort(cells, (x, y) -> {
			int dx = Math.abs(x[0] - r0) + Math.abs(x[1] - c0);
			int dy = Math.abs(y[0] - r0) + Math.abs(y[1] - c0);
			return dx - dy;
		});
		return cells.toArray(new int[cells.size()][2]);
	}
}
