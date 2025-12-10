package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 04/04/2017.
 * #0505 https://leetcode.com/problems/the-maze-ii/
 */
public class TheMazeII {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(m * n * log(m * n)), space O(m * n)
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
		// 与#2290相似
        int m = maze.length, n = maze[0].length, sx = start[0], sy = start[1],
                dx = destination[0], dy = destination[1];
        int[][] distance = new int[m][n];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy, 0});
        distance[sx][sy] = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = curr[0], y = curr[1], steps = 0;
                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                    x += DIRS[i];
                    y += DIRS[i + 1];
                    steps++;
                }
                x -= DIRS[i];
                y -= DIRS[i + 1];
                steps--;
                if (distance[x][y] > distance[curr[0]][curr[1]] + steps) {
                    queue.offer(new int[]{x, y, distance[curr[0]][curr[1]] + steps});
                    distance[x][y] = distance[curr[0]][curr[1]] + steps;
                }
            }
        }
        return distance[dx][dy] == Integer.MAX_VALUE ? -1 : distance[dx][dy];
    }
}
