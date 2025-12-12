package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 07/19/2017.
 * #0317 https://leetcode.com/problems/shortest-distance-from-all-buildings/
 */
public class ShortestDistanceFromAllBuildings {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(m * n * h), space O(m * n)
    public int shortestDistanceMultiBFS(int[][] grid) {
        int res = Integer.MAX_VALUE, m = grid.length, n = grid[0].length, houses = 0;
        int[][][] dist = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    bfs(i, j, grid, dist);
                    houses++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && dist[i][j][1] == houses) {
                    res = Math.min(res, dist[i][j][0]);
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void bfs(int sx, int sy, int[][] grid, int[][][] dist) {
        int cost = 0, m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy});
        visited[sx][sy] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int x = curr[0] + DIRS[i], y = curr[1] + DIRS[i + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0 && !visited[x][y]) {
                        visited[x][y] = true;
                        queue.offer(new int[]{x, y});
                        dist[x][y][0] += cost + 1;
                        dist[x][y][1]++;
                    }
                }
            }
            cost++;
        }
    }

    // time O(m * n * h), space O(m^2 * n^2)
    public int shortestDistanceSingleBFS(int[][] grid) {
        int res = Integer.MAX_VALUE, m = grid.length, n = grid[0].length, houses = 0;
        // 数组visited[x][y][sx][sy]记录的是从格子[sx, sy]出发的BFS是否已经遍历过格子[x, y]
        boolean[][][][] visited = new boolean[m][n][m][n];
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j, i, j, 0});
                    visited[i][j][i][j] = true;
                    grid[i][j] = -1;
                    houses++;
                } else if (grid[i][j] == 2) {
                    grid[i][j] = -2;
                }
            }
        }
        int[][] counts = new int[m][n];
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1], sx = curr[2], sy = curr[3], dist = curr[4];
            for (int i = 0; i < 4; i++) {
                int nx = x + DIRS[i], ny = y + DIRS[i + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] >= 0
                        && !visited[nx][ny][sx][sy]) {
                    visited[nx][ny][sx][sy] = true;
                    queue.offer(new int[]{nx, ny, sx, sy, dist + 1});
                    grid[nx][ny] += dist + 1;
                    counts[nx][ny]++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 注意：合格的空地需要能够到达所有的房子
                if (grid[i][j] >= 0 && counts[i][j] == houses) {
                    res = Math.min(res, grid[i][j]);
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // time O(m * n * h), space O(m^2 * n^2)
    public int shortestDistanceStateCompression(int[][] grid) {
        int res = Integer.MAX_VALUE, m = grid.length, n = grid[0].length, houses = 0;
        // 因为m和n都小于等于50，所以可以将表示状态的四个坐标压缩到一个32位整型中。
        Set<Integer> visited = new HashSet<>();
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j, i, j, 0});
                    visited.add(encode(i, j, i, j));
                    grid[i][j] = -1;
                    houses++;
                } else if (grid[i][j] == 2) {
                    grid[i][j] = -2;
                }
            }
        }
        int[][] counts = new int[m][n];
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1], sx = curr[2], sy = curr[3], dist = curr[4];
            for (int i = 0; i < 4; i++) {
                int nx = x + DIRS[i], ny = y + DIRS[i + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] >= 0
                        && !visited.contains(encode(nx, ny, sx, sy))) {
                    visited.add(encode(nx, ny, sx, sy));
                    queue.offer(new int[]{nx, ny, sx, sy, dist + 1});
                    grid[nx][ny] += dist + 1;
                    counts[nx][ny]++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] >= 0 && counts[i][j] == houses) {
                    res = Math.min(res, grid[i][j]);
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int encode(int x, int y, int sx, int sy) {
        int res = (x << 6) + y;
        res = (res << 6) + sx;
        return (res << 6) + sy;
    }
}
