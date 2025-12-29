package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/27/2019.
 * #1091 https://leetcode.com/problems/shortest-path-in-binary-matrix/
 */
public class ShortestPathInBinaryMatrix {

    private static final int[] DIRS = new int[]{1, -1, 0, -1, -1, 1, 1, 0, 1};

	// time O(m * n), space O(min(m, n))
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] != 0) {
            return -1;
        }
        int shortest = 1, n = grid.length;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        grid[0][0] = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                if (curr[0] == n - 1 && curr[1] == n - 1) {
                    return shortest;
                }
                for (int i = 0; i < 8; i++) {
                    int x = curr[0] + DIRS[i], y = curr[1] + DIRS[i + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                        grid[x][y] = -1;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
            shortest++;
        }
        return -1;
    }
}
