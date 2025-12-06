package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 12/06/2025.
 * #LCCI-16.19 https://leetcode.cn/problems/pond-sizes-lcci/
 */
public class PondSizesLCCI {

    private static final int[] DIRS = new int[]{1, -1, 0, -1, -1, 1, 1, 0, 1};

    // time O(m * n + k * log(k)), space O(m * n)
    public int[] pondSizesDFS(int[][] land) {
        int m = land.length, n = land[0].length;
        List<Integer> areas = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 0) {
                    areas.add(dfs(land, i, j));
                }
            }
        }
        Collections.sort(areas);
        int[] res = new int[areas.size()];
        for (int i = 0; i < areas.size(); i++) {
            res[i] = areas.get(i);
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j) {
        // 判断边界合法性以及当前格子是否已经遍历过
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 0) {
            return 0;
        }
        int res = 1;
        // 标记当前格子为遍历过
        grid[i][j] = -1;
        for (int d = 0; d < 8; d++) {
            res += dfs(grid, i + DIRS[d], j + DIRS[d + 1]);
        }
        return res;
    }

    // time O(m * n + k * log(k)), space O(min(m, n))
    public int[] pondSizesBFS(int[][] land) {
        int m = land.length, n = land[0].length;
        List<Integer> areas = new ArrayList<>();
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 0) {
                    int area = 0;
                    queue.offer(new int[]{i, j});
                    land[i][j] = -1;
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        area++;
                        for (int d = 0; d < 8; d++) {
                            int x = curr[0] + DIRS[d], y = curr[1] + DIRS[d + 1];
                            // 判断边界合法性以及当前格子是否已经遍历过
                            if (x >= 0 && x < m && y >= 0 && y < n && land[x][y] == 0) {
                                // 标记当前格子为遍历过
                                land[x][y] = -1;
                                queue.offer(new int[]{x, y});
                            }
                        }
                    }
                    areas.add(area);
                }
            }
        }
        Collections.sort(areas);
        int[] res = new int[areas.size()];
        for (int i = 0; i < areas.size(); i++) {
            res[i] = areas.get(i);
        }
        return res;
    }
}
