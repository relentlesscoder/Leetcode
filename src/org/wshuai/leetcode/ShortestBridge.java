package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/26/2019.
 * #0934 https://leetcode.com/problems/shortest-bridge/
 */
public class ShortestBridge {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

	// time O(m * n), space O(m * n)
    public int shortestBridge(int[][] grid) {
        int m = grid.length, n = grid[0].length, shortest = 0;
        boolean terminate = false;
        Deque<int[]> queue = new ArrayDeque<>();
		// DFS找到第一个岛，将岛中所有的格子加入队列
        for (int i = 0; i < m && !terminate; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, queue);
                    terminate = true;
                    break;
                }
            }
        }
		// BFS找到第一个岛以第二个岛的最短距离，即为需要翻转的0的数量
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int x = curr[0] + DIRS[i], y = curr[1] + DIRS[i + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
						// 找到第二个岛如果发现相邻的格子为1
                        if (grid[x][y] == 1) {
                            return shortest;
                        } else if (grid[x][y] == 0) {
                            queue.offer(new int[]{x, y});
                            grid[x][y] = -1;
                        }
                    }
                }
            }
            shortest++;
        }
        return -1;
    }

    private void dfs(int[][] grid, int i, int j, Deque<int[]> queue) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return;
        }
        queue.offer(new int[]{i, j});
        grid[i][j] = -1;
        for (int d = 0; d < 4; d++) {
            dfs(grid, i + DIRS[d], j + DIRS[d + 1], queue);
        }
    }
}
