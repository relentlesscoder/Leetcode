package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 03/23/2020.
 * #1391 https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/
 */
public class CheckIfThereIsAValidPathInAGrid {

    // time O(m * n), space O(m * n)
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        return dfs(grid, 0, 0, visited);
    }

    private boolean dfs(int[][] grid, int i, int j, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) {
            return false;
        }
        if (i == m - 1 && j == n - 1) {
            return true;
        }
        visited[i][j] = true;
        int v = grid[i][j];
        // Move left
        if (v == 1 || v == 3 || v == 5) {
            int x = i, y = j - 1;
            if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]
                    && (grid[x][y] == 1 || grid[x][y] == 4 || grid[x][y] == 6)) {
                if (dfs(grid, x, y, visited)) {
                    return true;
                }
            }
        }
        // Move right
        if (v == 1 || v == 4 || v == 6) {
            int x = i, y = j + 1;
            if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]
                    && (grid[x][y] == 1 || grid[x][y] == 3 || grid[x][y] == 5)) {
                if (dfs(grid, x, y, visited)) {
                    return true;
                }
            }
        }
        // Move up
        if (v == 2 || v == 5 || v == 6) {
            int x = i - 1, y = j;
            if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]
                    && (grid[x][y] == 2 || grid[x][y] == 3 || grid[x][y] == 4)) {
                if (dfs(grid, x, y, visited)) {
                    return true;
                }
            }
        }
        // Move down
        if (v == 2 || v == 3 || v == 4) {
            int x = i + 1, y = j;
            if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]
                    && (grid[x][y] == 2 || grid[x][y] == 5 || grid[x][y] == 6)) {
                if (dfs(grid, x, y, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    // time O(m * n), space O(min(m, n))
    public boolean hasValidPathBFS(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, grid[0][0]});
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int i = curr[0], j = curr[1], v = curr[2];
            if (i == m - 1 && j == n - 1) {
                return true;
            }
            // Move left
            if (v == 1 || v == 3 || v == 5) {
                int x = i, y = j - 1;
                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]
                        && (grid[x][y] == 1 || grid[x][y] == 4 || grid[x][y] == 6)) {
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y, grid[x][y]});
                }
            }
            // Move right
            if (v == 1 || v == 4 || v == 6) {
                int x = i, y = j + 1;
                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]
                        && (grid[x][y] == 1 || grid[x][y] == 3 || grid[x][y] == 5)) {
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y, grid[x][y]});
                }
            }
            // Move up
            if (v == 2 || v == 5 || v == 6) {
                int x = i - 1, y = j;
                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]
                        && (grid[x][y] == 2 || grid[x][y] == 3 || grid[x][y] == 4)) {
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y, grid[x][y]});
                }
            }
            // Move down
            if (v == 2 || v == 3 || v == 4) {
                int x = i + 1, y = j;
                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]
                        && (grid[x][y] == 2 || grid[x][y] == 5 || grid[x][y] == 6)) {
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y, grid[x][y]});
                }
            }
        }
        return false;
    }
}
