package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/03/2023.
 * #1970 https://leetcode.com/problems/last-day-where-you-can-still-cross/
 */
public class LastDayWhereYouCanStillCross {

	private int[] dirs = new int[]{0, -1, 0, 1, 0};

	// time O(r * c), space O(r * c)
	public int latestDayToCrossUnionFind(int row, int col, int[][] cells) {
		int n = row * col + 2, m = cells.length;
		UnionFind uf = new UnionFind(n);
		int[][] grid = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				grid[i][j] = 1;
			}
		}
		for (int i = 0; i < col; i++) {
			uf.union(i + 1, 0);
			uf.union((row - 1) * col + i + 1, n - 1);
		}
		for (int i = m - 1; i >= 0; i--) {
			int r = cells[i][0] - 1, c = cells[i][1] - 1;
			grid[r][c] = 0;
			int index = r * col + c + 1;
			for (int k = 0; k < 4; k++) {
				int x = r + dirs[k], y = c + dirs[k + 1];
				if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] == 1) {
					continue;
				}
				uf.union(index, x * col + y + 1);
			}
			if (uf.find(0) == uf.find(n - 1)) {
				return i;
			}
		}
		return -1;
	}

	// time O(r * c * log(n)), O(r * c)
	public int latestDayToCross(int row, int col, int[][] cells) {
		int n = cells.length, low = 0, high = n - 1;
		while (low < high) {
			int mid = (low + high + 1) >> 1;
			if (canWalkDFS(cells, row, col, mid)) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}

	private boolean canWalkDFS(int[][] cells, int m, int n, int day) {
		int[][] grid = new int[m][n];
		for (int i = 0; i < day; i++) {
			grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
		}
		for (int i = 0; i < n; i++) {
			if (grid[0][i] == 1 || grid[0][i] == -1) {
				continue;
			}
			if (dfs(grid, 0, i, m, n)) {
				return true;
			}
		}
		return false;
	}

	private boolean dfs(int[][] grid, int i, int j, int m, int n) {
		if (i == m - 1) {
			return true;
		}
		grid[i][j] = -1;
		for (int k = 0; k < 4; k++) {
			int x = i + dirs[k], y = j + dirs[k + 1];
			if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 1 || grid[x][y] == -1) {
				continue;
			}
			if (dfs(grid, x, y, m, n)) {
				return true;
			}
		}
		return false;
	}

	private boolean canWalkBFS(int[][] cells, int m, int n, int day) {
		int[][] grid = new int[m][n];
		for (int i = 0; i < day; i++) {
			grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
		}
		Deque<int[]> queue = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			if (grid[0][i] == 1) {
				continue;
			}
			queue.offer(new int[]{0, i});
			grid[0][i] = -1;
		}
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			if (curr[0] == m - 1) {
				return true;
			}
			for (int k = 0; k < 4; k++) {
				int x = curr[0] + dirs[k], y = curr[1] + dirs[k + 1];
				if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 1 || grid[x][y] == -1) {
					continue;
				}
				grid[x][y] = -1;
				queue.offer(new int[]{x, y});
			}
		}
		return false;
	}

	private class UnionFind {

		private int[] root, rank;

		public UnionFind(int n) {
			root = new int[n];
			rank = new int[n];
			for (int i = 0; i < n; i++) {
				root[i] = i;
				rank[i] = 1;
			}
		}

		public int find(int x) {
			if (x != root[x]) {
				root[x] = find(root[x]);
			}
			return root[x];
		}

		public void union(int x, int y) {
			int rootX = find(x), rootY = find(y);
			if (rootX == rootY) {
				return;
			}
			if (rank[rootX] > rank[rootY]) {
				rank[rootX] += rank[rootY];
				root[rootY] = rootX;
			} else {
				rank[rootY] += rank[rootX];
				root[rootX] = rootY;
			}
		}
	}
}
