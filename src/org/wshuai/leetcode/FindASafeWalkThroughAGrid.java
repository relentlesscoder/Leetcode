package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 12/09/2025.
 * #3286 https://leetcode.com/problems/find-a-safe-walk-through-a-grid/
 */
public class FindASafeWalkThroughAGrid {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(m * n), space O(m * n)
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        // 同#2290
        int m = grid.size(), n = grid.get(0).size();
        int[][] mat = new int[m][n], dis = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = grid.get(i).get(j);
                dis[i][j] = Integer.MAX_VALUE;
            }
        }
        dis[0][0] = mat[0][0];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offerFirst(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] curr = queue.pollFirst();
            int r = curr[0], c = curr[1];
            for (int i = 0; i < 4; i++) {
                int x = r + DIRS[i], y = c + DIRS[i + 1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    int cost = mat[x][y];
                    if (dis[x][y] > dis[r][c] + cost) {
                        dis[x][y] = dis[r][c] + cost;
                        if (cost == 0) {
                            queue.offerFirst(new int[]{x, y});
                        } else {
                            queue.offerLast(new int[]{x, y});
                        }
                    }
                }
            }
        }
        return dis[m - 1][n - 1] < health;
    }

    // time O(m * n * log(m * n)), space O(m * n)
    public boolean findSafeWalkDijkstra(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.get(0).size();
        int[][] mat = new int[m][n], dis = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = grid.get(i).get(j);
                dis[i][j] = Integer.MAX_VALUE;
            }
        }
        dis[0][0] = mat[0][0];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.offer(new int[]{0, 0, mat[0][0]});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];
            for (int i = 0; i < 4; i++) {
                int x = r + DIRS[i], y = c + DIRS[i + 1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    int cost = mat[x][y];
                    if (dis[x][y] > dis[r][c] + cost) {
                        dis[x][y] = dis[r][c] + cost;
                        queue.offer(new int[]{x, y, dis[x][y]});
                    }
                }
            }
        }
        return dis[m - 1][n - 1] < health;
    }
}
