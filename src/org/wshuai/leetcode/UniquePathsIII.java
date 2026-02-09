package org.wshuai.leetcode;

/**
 * Created by Wei on 09/10/2019.
 * #0980 https://leetcode.com/problems/unique-paths-iii/
 */
public class UniquePathsIII {

    private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};
    private int res = 0;

    public int uniquePathsIII(int[][] grid) {
        // #0079
        res = 0;
        int m = grid.length, n = grid[0].length,
                cnt = 0, // 空格子的数量
                sx = -1, sy = -1, // 起始方格
                tx = -1, ty = -1; // 结束方格
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    sx = i;
                    sy = j;
                } else if (grid[i][j] == 0) {
                    cnt++;
                } else if (grid[i][j] == 2) {
                    tx = i;
                    ty = j;
                }
            }
        }
        if (sx == -1 || tx == -1) { // 起始或者结束方格不存在
            return res;
        }
        dfs(cnt + 1, sx, sy, tx, ty, grid);
        return res;
    }

    private void dfs(int steps, int i, int j, int tx, int ty, int[][] grid) {
        if (steps == 0) { // 如果方格走完恰巧走到目标格子则找到一种方法
            res += (i == tx && j == ty) ? 1 : 0;
            return;
        }
        if (i == tx && j == ty) { // 优化: 过早的走到目标则直接终止
            return;
        }
        grid[i][j] = -1;
        for (int d = 0; d < 4; d++) {
            int x = i + DIRS[d], y = j + DIRS[d + 1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length
                    && (grid[x][y] == 0 || grid[x][y] == 2)) {
                dfs(steps - 1, x, y, tx, ty, grid);
            }
        }
        // 递归完恢复现场
        grid[i][j] = 0;
    }
}
