package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/01/2016.
 * #0286 https://leetcode.com/problems/walls-and-gates/
 */
public class WallsAndGates {
    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};
    private static final int INF = 2147483647;

    // time O(m * n), space O(min(m, n))
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length, n = rooms[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j, 0});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = curr[0] + DIRS[i], y = curr[1] + DIRS[i + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && rooms[x][y] == INF) {
                    rooms[x][y] = curr[2] + 1;
                    queue.offer(new int[]{x, y, rooms[x][y]});
                }
            }
        }
    }
}
