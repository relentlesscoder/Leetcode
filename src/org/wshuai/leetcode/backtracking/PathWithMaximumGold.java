package org.wshuai.leetcode.backtracking;

/**
 * Created by Wei on 10/09/2019.
 * #1219 https://leetcode.com/problems/path-with-maximum-gold/
 */
public class PathWithMaximumGold {

    private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};
    private int res = 0;

    // time O(t * 3^(min(m * n, t))), space O(min(m * n, t))
    public int getMaximumGold(int[][] grid) {
        res = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) { // O(t)
                    dfs(0, i, j, grid);
                }
            }
        }
        return res;
    }

    private void dfs(int sum, int i, int j, int[][] grid) {
        int gold = grid[i][j];
        res = Math.max(res, sum + gold);
        grid[i][j] = 0;
        for (int d = 0; d < 4; d++) {
            int x = i + DIRS[d], y = j + DIRS[d + 1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] > 0) {
                dfs(sum + gold, x, y, grid);
            }
        }
        // 递归完恢复现场
        grid[i][j] = gold;
    }
}
