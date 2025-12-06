package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/20/2023.
 * #1905 https://leetcode.com/problems/count-sub-islands/
 */
public class CountSubIslands {

    private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};
    private boolean child = true;

    // time O(m * n), space O(m * n)
    public int countSubIslandsDFS(int[][] grid1, int[][] grid2) {
        int res = 0, m = grid1.length, n = grid1[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    child = true;
                    dfs(grid1, grid2, i, j);
                    if (child) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid1, int[][] grid2, int i, int j) {
        // 判断边界合法性以及当前格子是否已经遍历过
        if (i < 0 || i >= grid2.length || j < 0 || j >= grid2[0].length || grid2[i][j] != 1) {
            return;
        }
		// 如果在当前DFS过程中所有grid2中为1的格子在grid1中也为1，那当前格子所在的
		// grid2中的岛屿一定是grid1中某个岛屿的子岛屿。
        if (grid1[i][j] != 1) {
            child = false;
        }
        // 标记当前格子为遍历过
        grid2[i][j] = 0;
        for (int d = 0; d < 4; d++) {
            dfs(grid1, grid2, i + DIRS[d], j + DIRS[d + 1]);
        }
    }

    // time O(m * n), space O(min(m, n))
    public int countSubIslandsBFS(int[][] grid1, int[][] grid2) {
        int res = 0, m = grid1.length, n = grid1[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    boolean child = true;
                    queue.offer(new int[]{i, j});
                    grid2[i][j] = 0;
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        if (grid1[curr[0]][curr[1]] != 1) {
                            child = false;
                        }
                        for (int d = 0; d < 4; d++) {
                            int x = curr[0] + DIRS[d], y = curr[1] + DIRS[d + 1];
                            // 判断边界合法性以及当前格子是否已经遍历过
                            if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1) {
                                // 标记当前格子为遍历过
                                grid2[x][y] = 0;
                                queue.offer(new int[]{x, y});
                            }
                        }
                    }
                    if (child) {
                        res++;
                    }
                }
            }
        }
        return res;
    }
}
