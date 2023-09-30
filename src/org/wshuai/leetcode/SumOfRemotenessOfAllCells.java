package org.wshuai.leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/30/2023.
 * #2852 https://leetcode.com/problems/sum-of-remoteness-of-all-cells/
 */
public class SumOfRemotenessOfAllCells {

	private int[] dirs = new int[] {0, -1, 0, 1, 0};

	// time O(n^2), space O(n)
	public long sumRemoteness(int[][] grid) {
		long sumRemoteness = 0L, totalUnblockingCells = 0;
		int n = grid.length;
		List<long[]> islandSumList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] > 0) {
					long[] sum = dfs(grid, i, j, n);
					totalUnblockingCells += sum[1];
					islandSumList.add(sum);
				}
			}
		}
		for (long[] currentSum : islandSumList) {
			sumRemoteness += currentSum[0] * (totalUnblockingCells - currentSum[1]);
		}
		return sumRemoteness;
	}

	// time O(n^2), space O(n^2)
	public long sumRemotenessUnionFind(int[][] grid) {
		long res = 0;
		int n = grid.length;
		UnionFind uf = new UnionFind(grid);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] != -1) {
					grid[i][j] = -1;
					for (int k = 0; k < 4; k++) {
						int x = i + dirs[k], y = j + dirs[k + 1];
						if (x < 0 || x >= n || y < 0 || y >= n || grid[x][y] == -1) {
							continue;
						}
						uf.union(i * n + j, x * n + y);
					}
				}
			}
		}
		return uf.findTotalRemoteness();
	}

	private long[] dfs(int[][] grid, int i, int j, int n) {
		long sum = grid[i][j], count = 1;
		grid[i][j] = -1;
		for (int k = 0; k < 4; k++) {
			int r = i + dirs[k], c = j + dirs[k + 1];
			if (Math.min(r, c) >= 0 && Math.max(r, c) < n && grid[r][c] != -1) {
				long[] res = dfs(grid, r, c, n);
				sum += res[0];
				count += res[1];
			}
		}
		return new long[] {sum, count};
	}

	private class UnionFind {
		private int n, total;
		private int[] root, size;
		private long[] sum;

		public UnionFind(int[][] grid) {
			n = grid.length;
			total = 0;
			root = new int[n * n];
			size = new int[n * n];
			sum = new long[n * n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (grid[i][j] != -1) {
						int index = i * n + j;
						total++;
						root[index] = index;
						size[index] = 1;
						sum[index] = grid[i][j];
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
			if (size[rootX] > size[rootY]) {
				size[rootX] += size[rootY];
				sum[rootX] += sum[rootY];
				root[rootY] = rootX;
			} else {
				size[rootY] += size[rootX];
				sum[rootY] += sum[rootX];
				root[rootX] = rootY;
			}
		}

		public long findTotalRemoteness() {
			long res = 0;
			for (int i = 0; i < root.length; i++) {
				if (find(i) == i) {
					res += sum[i] * (total - size[i]);
				}
			}
			return res;
		}
	}
}
