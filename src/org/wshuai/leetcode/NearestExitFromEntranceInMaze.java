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
        int shortest = 0, m = maze.length, n = maze[0].length,
                sx = entrance[0], sy = entrance[1];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy});
        // 标记已遍历过的格子
        maze[sx][sy] = '#';
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int x = curr[0] + DIRS[i], y = curr[1] + DIRS[i + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == '.') {
                        // 小优化：提前判定以节省时间
                        if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
                            return shortest + 1;
                        }
                        maze[x][y] = '#';
                        queue.offer(new int[]{x, y});
                    }
                }
            }
            shortest++;
        }
        return -1;
    }
}
