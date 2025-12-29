package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 03/02/2021.
 * #1765 https://leetcode.com/problems/map-of-highest-peak/
 */
public class MapOfHighestPeak {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(m * n), space O(min(m, n))
    public int[][] highestPeak(int[][] isWater) {
        // #1162的变形题
        int height = 0, m = isWater.length, n = isWater[0].length;
        int[][] res = new int[m][n];
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    isWater[i][j] = -1;
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int x = curr[0] + DIRS[i], y = curr[1] + DIRS[i + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && isWater[x][y] == 0) {
                        res[x][y] = height + 1;
                        queue.offer(new int[]{x, y});
                        isWater[x][y] = -1;
                    }
                }
            }
            height++;
        }
        return res;
    }
}
