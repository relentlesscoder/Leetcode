package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/25/2020.
 * #1631 https://leetcode.com/problems/path-with-minimum-effort/
 */
public class PathWithMinimumEffort {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(m * n), space O(m * n)
    public int minimumEffortPathBFS(int[][] heights) {
        // #2290
        int m = heights.length, n = heights[0].length;
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 0});
        dist[0][0] = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];
            for (int i = 0; i < 4; i++) {
                int x = r + DIRS[i], y = c + DIRS[i + 1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    int diff = Math.abs(heights[x][y] - heights[r][c]);
                    if (dist[x][y] > Math.max(dist[r][c], diff)) {
                        dist[x][y] = Math.max(dist[r][c], diff);
                        queue.offer(new int[]{x, y, dist[x][y]});
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }

    // time O(m * n * log(m * n)), space O(m * n)
    public int minimumEffortPathDijkstra(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        minQueue.offer(new int[]{0, 0, 0});
        while (!minQueue.isEmpty()) {
            int[] curr = minQueue.poll();
            if (curr[0] == m - 1 && curr[1] == n - 1) {
                return curr[2];
            }
            for (int k = 0; k < 4; k++) {
                int x = curr[0] + DIRS[k], y = curr[1] + DIRS[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    int cost = Math.max(curr[2], Math.abs(heights[x][y] - heights[curr[0]][curr[1]]));
                    if (dist[x][y] > cost) {
                        dist[x][y] = cost;
                        minQueue.offer(new int[]{x, y, cost});
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }
}
