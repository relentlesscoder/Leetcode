package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 12/11/2025.
 * #LCP56 https://leetcode.cn/problems/6UEx57/
 */
public class LCP56 {

    private static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final char[] SYMBOL = new char[] {'^', 'v', '<', '>'};

    // time O(m * n), space O(m * n)
    public int conveyorBelt(String[] matrix, int[] start, int[] end) {
        // #2290相似题
        int m = matrix.length, n = matrix[0].length(), sx = start[0], sy = start[1],
                ex = end[0], ey = end[1];
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offerFirst(new int[] {sx, sy});
        dist[sx][sy] = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.pollFirst();
            int r = curr[0], c = curr[1];
            if (r == ex && c == ey) {
                return dist[r][c];
            }
            for (int i = 0; i < 4; i++) {
                int cost = SYMBOL[i] != matrix[r].charAt(c) ? 1 : 0;
                int x = r + DIRS[i][0], y = c + DIRS[i][1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    if (dist[x][y] > dist[r][c] + cost) {
                        dist[x][y] = dist[r][c] + cost;
                        if (cost == 0) {
                            queue.offerFirst(new int[] {x, y});
                        } else {
                            queue.offerLast(new int[] {x, y});
                        }
                    }
                }
            }
        }
        return -1;
    }
}
