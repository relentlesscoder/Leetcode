package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 12/05/2025.
 * #3619 https://leetcode.com/problems/count-islands-with-total-value-divisible-by-k/
 */
public class CountIslandsWithTotalValueDivisibleByK {

    private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};

    // time O(m * n), space O(m * n)
    public int countIslandsDFS(int[][] grid, int k) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > 0) {
                    long sum = dfs(grid, i, j);
                    if (sum % k == 0) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    private long dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        long res = grid[i][j];
        grid[i][j] = 0;
        for (int d = 0; d < 4; d++) {
            res += dfs(grid, i + DIRS[d], j + DIRS[d + 1]);
        }
        return res;
    }

    // time O(m * n), space O(min(m, n))
    public int countIslandsBFS(int[][] grid, int k) {
        int res = 0, m = grid.length, n = grid[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long sum = 0;
                    queue.offer(new int[]{i, j, grid[i][j]});
                    grid[i][j] = 0;
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        sum += curr[2];
                        for (int d = 0; d < 4; d++) {
                            int x = curr[0] + DIRS[d], y = curr[1] + DIRS[d + 1];
                            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
                                queue.offer(new int[]{x, y, grid[x][y]});
                                grid[x][y] = 0;
                            }
                        }
                    }
                    if (sum % k == 0) {
                        res++;
                    }
                }
            }
        }
        return res;
    }
}
