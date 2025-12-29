package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 10/29/2016.
 * #0417 https://leetcode.com/problems/pacific-atlantic-water-flow/
 */
public class PacificAtlanticWaterFlow {
    private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};

    // time O(m * n), space O(m * n)
    public List<List<Integer>> pacificAtlanticDFS(int[][] heights) {
        // 水可以从高的格子流入相邻的较低格子最终通过网格边界上的格子流入海洋，反
        // 向思考可以把问题看成从网格边界出发水可以从低的格子反向流入相邻的较高的
        // 格子里。最后所有能到达的格子都满足水可以从之出发并最终流入海洋这个要求。
        int m = heights.length, n = heights[0].length;
        boolean[][] visited1 = new boolean[m][n];
        boolean[][] visited2 = new boolean[m][n];
        // 分别对左上边界和右下边界的所有格子DFS，用两个不同的布尔数组来标记水在
        // 这两种情况下可以逆流到的所有格子的集合。这两个集合的交集就是答案。
        for (int i = 0; i < n; i++) {
            dfs(heights, 0, i, visited1);
            dfs(heights, m - 1, i, visited2);
        }
        for (int i = 0; i < m; i++) {
            dfs(heights, i, 0, visited1);
            dfs(heights, i, n - 1, visited2);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited1[i][j] && visited2[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid, int i, int j, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        visited[i][j] = true;
        for (int d = 0; d < 4; d++) {
            int x = i + DIRS[d], y = j + DIRS[d + 1];
            if (x >= 0 && x < m && y >= 0 && y < n
					&& grid[x][y] >= grid[i][j] && !visited[x][y]) {
                dfs(grid, x, y, visited);
            }
        }
    }

    // time O(m * n), space O(min(m, n))
    public List<List<Integer>> pacificAtlanticBFS(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        Deque<int[]> queue1 = new ArrayDeque<>();
        Deque<int[]> queue2 = new ArrayDeque<>();
        boolean[][] visited1 = new boolean[m][n];
        boolean[][] visited2 = new boolean[m][n];
        for (int i = 0; i < n; i++) {
            visited1[0][i] = true;
            visited2[m - 1][i] = true;
            queue1.offer(new int[]{0, i});
            queue2.offer(new int[]{m - 1, i});
        }
        for (int i = 0; i < m; i++) {
            visited1[i][0] = true;
            visited2[i][n - 1] = true;
            queue1.offer(new int[]{i, 0});
            queue2.offer(new int[]{i, n - 1});
        }
        bfs(heights, queue1, visited1);
        bfs(heights, queue2, visited2);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited1[i][j] && visited2[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void bfs(int[][] grid, Deque<int[]> queue, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int d = 0; d < 4; d++) {
                int x = curr[0] + DIRS[d], y = curr[1] + DIRS[d + 1];
                if (x >= 0 && x < m && y >= 0 && y < n
						&& grid[x][y] >= grid[curr[0]][curr[1]] && !visited[x][y]) {
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
        }
    }
}
