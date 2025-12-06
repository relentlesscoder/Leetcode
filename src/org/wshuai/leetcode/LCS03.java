package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 12/06/2025.
 * #LCS03 https://leetcode.cn/problems/YesdPw/
 */
public class LCS03 {

    private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};

    // time O(m * n), space O(m * n)
    public int largestAreaDFS(String[] grid) {
        int res = 0, m = grid.length, n = grid[0].length();
        int[][] mat = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = grid[i].charAt(j) - '0';
            }
        }
        // 对每个“走廊”的格子进行DFS以标记所有与走廊直接相邻的主题公园的格子，
        // 这些主题公园的格子在后续计算中不用统计。
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1 || mat[i][j] == 0) {
                    dfs1(mat, i, j, mat[i][j]);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == -1) {
                    continue;
                }
                res = Math.max(res, dfs(mat, i, j, mat[i][j]));
            }
        }
        return res;
    }

    private void dfs1(int[][] grid, int i, int j, int val) {
        // 判断边界合法性以及当前格子是否已经遍历过
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == -1) {
            return;
        }
        if (val > 0 && grid[i][j] != val) {
            return;
        }
        int v = grid[i][j];
        grid[i][j] = -1;
        for (int d = 0; d < 4; d++) {
            dfs1(grid, i + DIRS[d], j + DIRS[d + 1], v);
        }
    }

    // time O(m * n), space O(m * n)
    public int largestAreaBFSDFS(String[] grid) {
        int res = 0, m = grid.length, n = grid[0].length();
        int[][] mat = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = grid[i].charAt(j) - '0';
            }
        }
        // 对每个“走廊”的格子进行BFS以标记所有与走廊直接相邻的主题公园的格子，
        // 这些主题公园的格子在后续计算中不用统计。
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1 || mat[i][j] == 0) {
                    queue.offer(new int[]{i, j, mat[i][j]});
                    mat[i][j] = -1;
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        for (int d = 0; d < 4; d++) {
                            int x = curr[0] + DIRS[d], y = curr[1] + DIRS[d + 1];
                            // 网格四条边上的所有格子以及任何与值是0的网格相邻的格子都应该被标记
                            if (x >= 0 && x < m && y >= 0 && y < n && mat[x][y] != -1 &&
                                    ((curr[2] == 0 && mat[x][y] >= 0) || (curr[2] > 0 && mat[x][y] == curr[2]))) {
                                queue.offer(new int[]{x, y, mat[x][y]});
                                mat[x][y] = -1;
                            }
                        }
                    }
                }
            }
        }
        // 对剩余的格子代表的所有主题公园进行统计并维护最大值
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == -1) {
                    continue;
                }
                res = Math.max(res, dfs(mat, i, j, mat[i][j]));
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j, int val) {
        // 判断边界合法性以及当前格子是否已经遍历过
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != val) {
            return 0;
        }
        int res = 1;
        // 标记当前格子为遍历过
        grid[i][j] = -1;
        for (int d = 0; d < 4; d++) {
            res += dfs(grid, i + DIRS[d], j + DIRS[d + 1], val);
        }
        return res;
    }
}
