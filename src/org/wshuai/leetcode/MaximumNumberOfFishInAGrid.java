package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/30/2023.
 * #2658 https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/
 */
public class MaximumNumberOfFishInAGrid {

    private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};

    // time O(m * n), space O(m * n)
    public int findMaxFishDFS(int[][] grid) {
        int res = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    res = Math.max(res, dfs(grid, i, j));
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j) {
        // 判断边界合法性以及当前格子是否已经遍历过
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        int res = grid[i][j];
        // 标记当前格子为遍历过
        grid[i][j] = 0;
        for (int d = 0; d < 4; d++) {
            res += dfs(grid, i + DIRS[d], j + DIRS[d + 1]);
        }
        return res;
    }

    // time O(m * n), space O(min(m, n))
    public int findMaxFishBFS(int[][] grid) {
        int res = 0, m = grid.length, n = grid[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    queue.offer(new int[]{i, j, grid[i][j]});
                    grid[i][j] = 0;
                    int fishes = 0;
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        fishes += curr[2];
                        for (int d = 0; d < 4; d++) {
                            int x = curr[0] + DIRS[d], y = curr[1] + DIRS[d + 1];
                            // 判断边界合法性以及当前格子是否已经遍历过
                            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
                                queue.offer(new int[]{x, y, grid[x][y]});
                                // 标记当前格子为遍历过
                                grid[x][y] = 0;
                            }
                        }
                    }
                    res = Math.max(res, fishes);
                }
            }
        }
        return res;
    }
}
