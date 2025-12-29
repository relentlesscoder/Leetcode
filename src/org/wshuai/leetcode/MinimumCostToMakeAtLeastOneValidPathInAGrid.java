package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 03/01/2020.
 * #1368 https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/
 */
public class MinimumCostToMakeAtLeastOneValidPathInAGrid {

    private static final int[][] DIRS = new int[][]{
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    // time O(m * n), space O(m * n)
    public int minCost(int[][] grid) {
        // #2290的变形题
        int m = grid.length, n = grid[0].length;
        int[][] dis = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dis[i][j] = Integer.MAX_VALUE;
            }
        }
        dis[0][0] = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offerFirst(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] curr = queue.pollFirst();
            int r = curr[0], c = curr[1];
            for (int i = 0; i < 4; i++) {
                int x = r + DIRS[i][0], y = c + DIRS[i][1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    // 仅当当前数字与行进方向一致时，不用付出额外代价来修改方向
                    int cost = grid[r][c] == i + 1 ? 0 : 1;
                    if (dis[x][y] > dis[r][c] + cost) {
                        dis[x][y] = dis[r][c] + cost;
                        if (cost == 1) {
                            queue.offerLast(new int[]{x, y});
                        } else {
                            queue.offerFirst(new int[]{x, y});
                        }
                    }
                }
            }
        }
        return dis[m - 1][n - 1];
    }

    // time O(m * n), space O(m * n)
    public int minCostDijkstra(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dis = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dis[i][j] = Integer.MAX_VALUE;
            }
        }
        dis[0][0] = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 0});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];
            for (int i = 0; i < 4; i++) {
                int x = r + DIRS[i][0], y = c + DIRS[i][1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    int cost = grid[r][c] == i + 1 ? 0 : 1;
                    if (dis[x][y] > dis[r][c] + cost) {
                        dis[x][y] = dis[r][c] + cost;
                        queue.offer(new int[]{x, y, dis[x][y]});
                    }
                }
            }
        }
        return dis[m - 1][n - 1];
    }
}
