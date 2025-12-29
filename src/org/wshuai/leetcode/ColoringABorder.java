package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/28/2019.
 * #1034 https://leetcode.com/problems/coloring-a-border/
 */
public class ColoringABorder {
    private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};

	// time O(m * n), space O(m * n)
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length, n = grid[0].length;
        List<int[]> borders = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];
		// 从当前格子执行DFS找到所有边界
        dfs(grid, row, col, grid[row][col], visited, borders);
		// 对所有边界着色
        for (int[] cell : borders) {
            grid[cell[0]][cell[1]] = color;
        }
        return grid;
    }

	private void dfs(int[][] grid, int x, int y, int id, boolean[][] visited, List<int[]> borders) {
		int m = grid.length, n = grid[0].length;
		// 当前格子在网格的边界上
		boolean isBorder = false;
		if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
			isBorder = true;
		}
		visited[x][y] = true;
		int count = 0;
		for (int d = 0; d < 4; d++) {
			int r = x + DIRS[d], c = y + DIRS[d + 1];
			// 遍历当前格子的四个相邻格子，判断他们中有几个属于当前连通分量。
			if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == id) {
				count++;
				if (!visited[r][c]) {
					dfs(grid, r, c, id, visited, borders);
				}
			}
		}
		// 根据题目描述，如果格子在相同的连通分量并满足以下条件之一：
		//   1. 在网格的边界上
		//   2. 与至少一个任意不同连通分量的格子相邻 (相邻的相同连通分量的格子总数小与4)
		// 当前格子为连通分量边界
		if (isBorder || count < 4) {
			borders.add(new int[] {x, y});
		}
	}
}
