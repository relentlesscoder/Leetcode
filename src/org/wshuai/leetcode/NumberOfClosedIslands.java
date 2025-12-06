package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/13/2019.
 * #1254 https://leetcode.com/problems/number-of-closed-islands/
 */
public class NumberOfClosedIslands {

    private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};

	private boolean closed = true;

	// time O(m * n), space O(m * n)
	public int closedIslandDFSWithStatus(int[][] grid) {
		int res = 0, m = grid.length, n = grid[0].length;
		// 遍历所有在网格上的值为0的格子（土地），使用DFS来标记所有与这些格子
		// 连通的值为0的格子-这些共同组成所有的岛屿。
		for (int i = 1; i < m - 1; i++) {
			for (int j = 1; j < n - 1; j++) {
				if (grid[i][j] == 0) {
					closed = true;
					dfsWithStatue(grid, i, j);
					if (closed) {
						res++;
					}
				}
			}
		}
		return res;
	}

	private void dfsWithStatue(int[][] grid, int r, int c) {
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != 0) {
			return;
		}
		if (r == 0 || r == grid.length - 1 || c == 0 || c == grid[0].length - 1) {
			closed = false;
		}
		grid[r][c] = -1;
		for (int d = 0; d < 4; d++) {
			int x = r + DIRS[d], y = c + DIRS[d + 1];
			dfsWithStatue(grid, x, y);
		}
	}

    // time O(m * n), space O(m * n)
    public int closedIslandDFS(int[][] grid) {
        int res = 0, m = grid.length, n = grid[0].length;
        // 遍历所有在网格外围上的值为0的格子（土地），使用DFS来标记所有与这些格子
        // 连通的值为0的格子-这些共同组成所有的开放岛屿。
        int[] cols = new int[]{0, n - 1};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[j][cols[i]] == 0) {
                    dfs(grid, j, cols[i]);
                }
            }
        }
        int[] rows = new int[]{0, m - 1};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[rows[i]][j] == 0) {
                    dfs(grid, rows[i], j);
                }
            }
        }
        // 再次遍历网格上余下的值为0的格子，这些格子组成所有的封闭岛屿。复用DFS代码
        // 统计次数
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 0) {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != 0) {
            return;
        }
        grid[r][c] = -1;
        for (int d = 0; d < 4; d++) {
            int x = r + DIRS[d], y = c + DIRS[d + 1];
            dfs(grid, x, y);
        }
    }

    // time O(m * n), space O(min(m, n))
    public int closedIslandBFS(int[][] grid) {
        int res = 0, m = grid.length, n = grid[0].length;
        // 与上面DFS解的方法一样，区别只是使用BFS来搜索所有组成岛屿的格子
        int[] cols = new int[]{0, n - 1};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[j][cols[i]] == 0) {
                    bfs(grid, j, cols[i]);
                }
            }
        }
        int[] rows = new int[]{0, m - 1};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[rows[i]][j] == 0) {
                    bfs(grid, rows[i], j);
                }
            }
        }
        // 再次遍历网格上余下的值为0的格子，这些格子组成所有的封闭岛屿。复用DFS代码
        // 统计次数
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 0) {
                    bfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void bfs(int[][] grid, int r, int c) {
        int m = grid.length, n = grid[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r, c});
        grid[r][c] = -1;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int d = 0; d < 4; d++) {
                int x = curr[0] + DIRS[d], y = curr[1] + DIRS[d + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0) {
                    queue.offer(new int[]{x, y});
                    grid[x][y] = -1;
                }
            }
        }
    }
}
