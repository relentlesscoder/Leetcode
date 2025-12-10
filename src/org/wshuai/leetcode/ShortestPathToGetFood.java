package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 02/27/2021.
 * #1730 https://leetcode.com/problems/shortest-path-to-get-food/
 */
public class ShortestPathToGetFood {

    // time O(m*n), space O(m*n)
    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(m * n), space O(min(m, n))
    public int getFood(char[][] grid) {
        int shortest = 0, m = grid.length, n = grid[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        boolean terminate = false;
        for (int i = 0; i < m && !terminate; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '*') {
                    queue.offer(new int[]{i, j});
                    grid[i][j] = 'X';
                    terminate = true;
                    break;
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int x = curr[0] + DIRS[d], y = curr[1] + DIRS[d + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != 'X') {
                        if (grid[x][y] == '#') {
                            return shortest + 1;
                        }
                        queue.offer(new int[]{x, y});
                        grid[x][y] = 'X';
                    }
                }
            }
            shortest++;
        }
        return -1;
    }
}
