package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 12/17/2025.
 * #3568 https://leetcode.com/problems/minimum-moves-to-clean-the-classroom/
 */
public class MinimumMovesToCleanTheClassroom {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};
    private static final int R = (int) 1e6;
    private static final int X = -1;

    // time O(m * n * e * 2^g), space O(m * n * e * 2^g)
    public int minMoves(String[] classroom, int energy) {
        // #0864相似题
        int steps = 0, m = classroom.length, n = classroom[0].length(), sx = 0, sy = 0, garbages = 0;
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    sx = i;
                    sy = j;
                } else if (c == 'L') {
                    grid[i][j] = 1 << garbages++;
                } else if (c == 'X') {
                    grid[i][j] = X;
                } else if (c == 'R') {
                    grid[i][j] = R;
                }
            }
        }
        int finished = (1 << garbages) - 1;
        boolean[][][][] visited = new boolean[m][n][energy + 1][1 << garbages];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy, energy, 0});
        visited[sx][sy][energy][0] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                if (curr[3] == finished) {
                    return steps;
                }
                int i = curr[0], j = curr[1], e = curr[2], g = curr[3];
                if (grid[i][j] == R) {
                    e = energy;
                } else if (e == 0) {
                    continue;
                }
                for (int d = 0; d < 4; d++) {
                    int x = i + DIRS[d], y = j + DIRS[d + 1];
                    if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == X) {
                        continue;
                    }
                    if (grid[x][y] > 0 && grid[x][y] != R) {
                        int state = (g | grid[x][y]);
                        if (!visited[x][y][e - 1][state]) {
                            visited[x][y][e - 1][state] = true;
                            queue.offer(new int[]{x, y, e - 1, state});
                        }
                    } else if (!visited[x][y][e - 1][g]) {
                        visited[x][y][e - 1][g] = true;
                        queue.offer(new int[]{x, y, e - 1, g});
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}
