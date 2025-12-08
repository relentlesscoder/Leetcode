package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/01/2020.
 * #1559 https://leetcode.com/problems/detect-cycles-in-2d-grid/
 */
public class DetectCyclesIn2DGrid {

	// time O(m * n), space O(m * n)
	public boolean containsCycleUnionFind(char[][] grid) {
		int m = grid.length, n = grid[0].length;
		UnionFind uf = new UnionFind(m * n);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (j + 1 < n && grid[i][j + 1] == grid[i][j]) {
					if (uf.union(i * n + j, i * n + j + 1)) {
						return true;
					}
				}
				if (i + 1 < m && grid[i + 1][j] == grid[i][j]) {
					if (uf.union(i * n + j, (i + 1) * n + j)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private static class UnionFind {

		private final int[] roots;
		private final int[] ranks;

		public UnionFind(int n) {
			roots = new int[n];
			ranks = new int[n];
			Arrays.setAll(roots, i -> i);
			Arrays.fill(ranks, 1);
		}

		public boolean union(int a, int b) {
			int ra = find(a), rb = find(b);
			if (ra == rb) {
				return true;
			}
			if (ranks[ra] > ranks[rb]) {
				ranks[ra] += ranks[rb];
				roots[rb] = ra;
			} else {
				ranks[rb] += ranks[ra];
				roots[ra] = rb;
			}
			return false;
		}

		private int find(int a) {
			if (roots[a] != a) {
				roots[a] = find(roots[a]);
			}
			return roots[a];
		}
	}

    private boolean hasRing = false;

    // time O(m * n), space O(m * n)
    public boolean containsCycleDFS(char[][] grid) {
        hasRing = false;
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    // 因为当前格子左上方向的格子都已经被遍历过，所以可以直接把方向参数设为L
                    dfs(grid, grid[i][j], i, j, 'L', visited);
                    if (hasRing) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void dfs(char[][] grid, char v, int x, int y, char from, boolean[][] visited) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != v) {
            return;
        }
        // 如果当前格子已经被遍历过说明存在环
        if (visited[x][y]) {
            hasRing = true;
            return;
        }
        visited[x][y] = true;
        // 通过判断遍历来自的方向避免无限循环
        if (from != 'L') dfs(grid, v, x, y - 1, 'R', visited);
        if (from != 'R') dfs(grid, v, x, y + 1, 'L', visited);
        if (from != 'U') dfs(grid, v, x - 1, y, 'D', visited);
        if (from != 'D') dfs(grid, v, x + 1, y, 'U', visited);
    }
}
