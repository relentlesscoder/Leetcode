package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/04/2019.
 * #0695 https://leetcode.com/problems/max-area-of-island/
 */
public class MaxAreaOfIsland {

    private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};

    // time O(m * n), space O(m * n)
    public int maxAreaOfIslandDFS(int[][] grid) {
		// #0200的变形题
        int res = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(grid, i, j));
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j) {
        // 判断边界合法性以及当前格子是否已经遍历过
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return 0;
        }
        int res = 1;
        // 标记当前格子为遍历过
        grid[i][j] = 2;
        for (int d = 0; d < 4; d++) {
            res += dfs(grid, i + DIRS[d], j + DIRS[d + 1]);
        }
        return res;
    }

    // time O(m * n), space O(min(m, n))
    public int maxAreaOfIslandBFS(int[][] grid) {
        int res = 0, m = grid.length, n = grid[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    grid[i][j] = 2;
                    int area = 0;
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        area++;
                        for (int d = 0; d < 4; d++) {
                            int x = curr[0] + DIRS[d], y = curr[1] + DIRS[d + 1];
                            // 判断边界合法性以及当前格子是否已经遍历过
                            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                                // 标记当前格子为遍历过
                                grid[x][y] = 2;
                                queue.offer(new int[]{x, y});
                            }
                        }
                    }
                    res = Math.max(res, area);
                }
            }
        }
        return res;
    }
}
