package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/26/2019.
 * #0542 https://leetcode.com/problems/01-matrix/
 */
public class ZeroOneMatrix {
    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(m * n), space O(min(m, n))
    public int[][] updateMatrix(int[][] mat) {
        // #1162的变形题
        int m = mat.length, n = mat[0].length, distance = 0;
        int[][] res = new int[m][n];
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    mat[i][j] = -1;
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int x = curr[0] + DIRS[i], y = curr[1] + DIRS[i + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && mat[x][y] == 1) {
                        res[x][y] = distance + 1;
                        queue.offer(new int[]{x, y});
                        mat[x][y] = -1;
                    }
                }
            }
            distance++;
        }
        return res;
    }
}
