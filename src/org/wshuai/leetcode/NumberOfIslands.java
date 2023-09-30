package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/24/2016.
 * #0200 https://leetcode.com/problems/number-of-islands/
 */
public class NumberOfIslands {

	private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};

	// DFS: time O(m * n), space O(m * n)
	// BFS: time O(m * n), space O(m + n)
	public int numIslands(char[][] grid) {
		int res = 0, m = grid.length, n = grid[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					res++;
					dfs(grid, i, j, m, n);
					//bfs(grid, i, j, m, n);
				}
			}
		}
		return res;
	}

	// time O(m * n), space O(m * n)
	public int numIslandsUnionFind(char[][] grid) {
		int m = grid.length, n = grid[0].length;
		UnionFind uf = new UnionFind(grid);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') { // for each node in "graph", union its neighbors
					grid[i][j] = '0'; // set it to '0' to avoid unnecessary unions
					for (int k = 0; k < 4; k++) {
						int x = i + DIRS[k], y = j + DIRS[k + 1];
						if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '0') {
							continue;
						}
						uf.union(i * n + j, x * n + y);
					}
				}
			}
		}
		return uf.countComponents();
	}

	private void dfs(char[][] grid, int i, int j, int m, int n) {
		grid[i][j] = '0';
		for (int k = 0; k < 4; k++) {
			int x = i + DIRS[k], y = j + DIRS[k + 1];
			if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
				dfs(grid, x, y, m, n);
			}
		}
	}

	private void bfs(char[][] grid, int i, int j, int m, int n) {
		Deque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{i, j});
		grid[i][j] = '0';
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			for (int k = 0; k < 4; k++) {
				int x = curr[0] + DIRS[k], y = curr[1] + DIRS[k + 1];
				if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '0') {
					continue;
				}
				grid[x][y] = '0';
				queue.offer(new int[]{x, y});
			}
		}
	}

	private class UnionFind {

		private int count, m, n;
		private int[] root, rank;

		public UnionFind(char[][] grid) {
			m = grid.length;
			n = grid[0].length;
			count = 0;
			root = new int[m * n];
			rank = new int[m * n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (grid[i][j] == '1') {
						int index = i * n + j;
						count++;
						root[index] = index;
						rank[index] = 1;
					}
				}
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
			count--;
		}

		public int countComponents() {
			return count;
		}
	}
}
