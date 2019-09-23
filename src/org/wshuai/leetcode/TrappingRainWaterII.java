package org.wshuai.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/8/16.
 * #407 https://leetcode.com/problems/trapping-rain-water-ii/
 */
public class TrappingRainWaterII {
	//BFS: http://www.cnblogs.com/grandyang/p/5928987.html
	public int trapRainWater(int[][] heightMap) {
		if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
			return 0;
		}
		int[] vs = new int[]{0, 0, -1, 1};
		int[] hs = new int[]{-1, 1, 0, 0};

		int rows = heightMap.length;
		int cols = heightMap[0].length;
		int max = Integer.MIN_VALUE;
		int sum = 0;
		PriorityQueue<Cell> pq = new PriorityQueue<Cell>(rows * cols, new CellComparator());

		boolean[] visited = new boolean[rows * cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i == 0 || j == 0 || i == rows - 1 || j == cols - 1) {
					visited[i * cols + j] = true;
					pq.offer(new Cell(i, j, heightMap[i][j]));
				}
			}
		}

		while (!pq.isEmpty()) {
			Cell top = pq.poll();
			int ch = top.val;
			int x = top.row;
			int y = top.col;
			max = ch > max ? ch : max;
			for (int i = 0; i < 4; i++) {
				int r = x + vs[i];
				int c = y + hs[i];
				if (r >= 0 && r < rows && c >= 0 && c < cols && !visited[r * cols + c]) {
					visited[r * cols + c] = true;
					if (heightMap[r][c] < max) {
						sum += max - heightMap[r][c];
					}
					pq.offer(new Cell(r, c, heightMap[r][c]));
				}
			}
		}

		return sum;
	}
}

class CellComparator implements Comparator<Cell> {
	@Override
	public int compare(Cell x, Cell y) {
		return x.val - y.val;
	}
}

class Cell {
	int row;
	int col;
	int val;

	public Cell(int row, int col, int val) {
		this.row = row;
		this.col = col;
		this.val = val;
	}
}
