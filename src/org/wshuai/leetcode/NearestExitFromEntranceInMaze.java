package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/25/2023.
 * #1926 https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/
 */
public class NearestExitFromEntranceInMaze {

    private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};

    // time O(m * n), space O(min(m, n))
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length, n = maze[0].length;
        int sx = entrance[0], sy = entrance[1];
        boolean[][] visited = new boolean[m][n];
        Deque<int[]> queue = new ArrayDeque<>();
        visited[sx][sy] = true;
        queue.offer(new int[]{sx, sy, 0});
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                int step = curr[2];
                for (int i = 0; i < 4; i++) {
                    int x = curr[0] + DIRS[i], y = curr[1] + DIRS[i + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == '.' && !visited[x][y]) {
                        if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
                            return step + 1;
                        }
                        visited[x][y] = true;
                        queue.offer(new int[]{x, y, step + 1});
                    }
                }
            }
        }
        return -1;
    }
}
