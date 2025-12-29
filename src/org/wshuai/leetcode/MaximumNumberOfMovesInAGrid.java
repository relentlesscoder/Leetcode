package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/01/2023.
 * #2684 https://leetcode.com/problems/maximum-number-of-moves-in-a-grid/
 */
public class MaximumNumberOfMovesInAGrid {

    private static final int[][] DIRS = new int[][]{{-1, 1}, {0, 1}, {1, 1}};

    private int max = 0;

    // time O(m * n), space O(n)
    public int maxMovesDFS(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            // 因为我们只能向右移动，所以DFS的最大深度是n
            dfs(grid, i, 0);
        }
        return max;
    }

    private void dfs(int[][] grid, int i, int j) {
        max = Math.max(max, j);
        for (int d = 0; d < 3; d++) {
            int x = i + DIRS[d][0], y = j + DIRS[d][1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] > grid[i][j]) {
                dfs(grid, x, y);
            }
        }
        // 如果我们已经能到达这个格子，那后续的遍历到达这个格子也没办法使结果更优
        // 因此可以把格子的值直接设为0以免重复遍历，当然也可以使用一个数组来记录
        // 已遍历过的格子。
        grid[i][j] = 0;
    }

    // time O(m * n), space O(m)
    public int maxMovesBFS(int[][] grid) {
        int res = 0, m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            visited[i][0] = true;
            queue.offer(new int[]{i, 0});
        }
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int i = curr[0], j = curr[1], v = grid[i][j];
            res = Math.max(res, j);
            for (int d = 0; d < 3; d++) {
                int x = i + DIRS[d][0], y = j + DIRS[d][1];
                if (x >= 0 && x < m && y >= 0 && y < n
                        && !visited[x][y] && grid[x][y] > v) {
                    queue.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }
        return res;
    }

    // time O(m * n), space O(m * n)
    public int maxMovesDP(int[][] grid) {
        int res = 0, row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < col; j++) {
            for (int i = 0; i < row; i++) {
                if (i - 1 >= 0 && grid[i][j] > grid[i - 1][j - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1]);
                }
                if (grid[i][j] > grid[i][j - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }
                if (i + 1 < row && grid[i][j] > grid[i + 1][j - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1]);
                }
                if (dp[i][j] > 0) {
                    dp[i][j]++;
                }
                res = Math.max(dp[i][j] - 1, res);
            }
        }
        return res;
    }
}
