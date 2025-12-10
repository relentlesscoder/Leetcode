package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 04/04/2017.
 * #0490 https://leetcode.com/problems/the-maze/
 */
public class TheMaze {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(m * n), space O(m * n)
    public boolean hasPathDFS(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length, sx = start[0], sy = start[1],
                dx = destination[0], dy = destination[1];
        boolean[][] visited = new boolean[m][n];
        return dfs(maze, sx, sy, dx, dy, visited);
    }

    private boolean dfs(int[][] grid, int i, int j, int dx, int dy, boolean[][] visited) {
        if (i == dx && j == dy) {
            return true;
        }
        visited[i][j] = true;
        for (int d = 0; d < 4; d++) {
            int x = i, y = j;
            while (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 0) {
                x += DIRS[d];
                y += DIRS[d + 1];
            }
            x -= DIRS[d];
            y -= DIRS[d + 1];
            if (!visited[x][y] && dfs(grid, x, y, dx, dy, visited)) {
                return true;
            }
        }
        return false;
    }

    // time O(m * n), space O(min(m, n))
    public boolean hasPathBFS(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length, sx = start[0], sy = start[1],
                dx = destination[0], dy = destination[1];
        boolean[][] visited = new boolean[m][n];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy});
        visited[sx][sy] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = curr[0], y = curr[1];
                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                    x += DIRS[i];
                    y += DIRS[i + 1];
                }
                x -= DIRS[i];
                y -= DIRS[i + 1];
                if (x == dx && y == dy) {
                    return true;
                }
                if (!visited[x][y]) {
                    queue.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }
        return false;
    }
}
