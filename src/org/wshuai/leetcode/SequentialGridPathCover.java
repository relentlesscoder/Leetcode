package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 02/09/2026.
 * #3565 https://leetcode.com/problems/sequential-grid-path-cover/
 */
public class SequentialGridPathCover {

    private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};

    // time O((m * n - k) * 3^(m * n)), space O(m * n)
    public List<List<Integer>> findPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        List<List<Integer>> res = new ArrayList<>();
        boolean found = false;
        for (int i = 0; i < m && !found; i++) {
            for (int j = 0; j < n; j++) { // 起始方格的值只能为 0 或者 1
                if (grid[i][j] <= 1 && dfs(grid[i][j] + 1, i, j, res, grid)) {
                    found = true;
                    break;
                }
            }
        }
        return res;
    }

    private boolean dfs(int next, int i, int j, List<List<Integer>> path, int[][] grid) {
        int m = grid.length, n = grid[0].length;
        path.add(List.of(i, j)); // 注意为了下面判断是否走完，这里要先把方格加入路径
        if (path.size() == m * n) { // 所有方格都能走完则找到答案
            return true;
        }
        int v = grid[i][j];
        grid[i][j] = -1;
        for (int d = 0; d < 4; d++) {
            int x = i + DIRS[d], y = j + DIRS[d + 1];
            // 只有当下一个格子是空 (0) 或者下一个要找的值 (next) 才可以继续搜索
            if (x >= 0 && x < m && y >= 0 && y < n && (grid[x][y] == next || grid[x][y] == 0)) {
                // 这里注意维护下一个要找的值 next
                if (dfs(next + (grid[x][y] == next ? 1 : 0), x, y, path, grid)) {
                    return true;
                }
            }
        }
        // 递归完恢复现场
        path.remove(path.size() - 1);
        grid[i][j] = v;
        return false;
    }
}
