package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/06/2019.
 * #0994 https://leetcode.com/problems/rotting-oranges/
 */
public class RottingOranges {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(m * n), space O(min(m, n))
    public int orangesRotting(int[][] grid) {
        int minutes = 0, count = 0, m = grid.length, n = grid[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        // 遍历数组，将所有已经腐烂的橘子加入FIFO队列
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                    grid[i][j] = 0;
                } else if (grid[i][j] == 1) {
                    count++;
                }
            }
        }
        // 直接返回0如果没有找到任何新鲜的橘子
        if (count == 0) {
            return 0;
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int x = curr[0] + DIRS[i], y = curr[1] + DIRS[i + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        // 返回当前时间+1如果所有新鲜橘子都已被遍历到
                        if (--count == 0) {
                            return minutes + 1;
                        }
                        queue.offer(new int[]{x, y});
                        grid[x][y] = 0;
                    }
                }
            }
            minutes++;
        }
        // 返回-1如果并非所有新鲜的橘子都能被BFS遍历到（腐烂）
        return -1;
    }
}
