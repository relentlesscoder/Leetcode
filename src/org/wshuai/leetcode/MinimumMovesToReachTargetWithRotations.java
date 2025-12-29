package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 11/22/2019.
 * #1210 https://leetcode.com/problems/minimum-moves-to-reach-target-with-rotations/
 */
public class MinimumMovesToReachTargetWithRotations {

    // time O(n * n), space O(n * n)
    public int minimumMoves(int[][] grid) {
        int moves = 0, n = grid.length;
        Set<Integer> visited = new HashSet<>();
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 1, 0, 0});
        visited.add(encode(0, 1, 0, 0));
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                int r1 = curr[0], c1 = curr[1], r2 = curr[2], c2 = curr[3];
                if (r1 == n - 1 && c1 == n - 1 && r2 == n - 1 && c2 == n - 2) {
                    return moves;
                }
                if (r1 == r2 && c1 == c2 + 1) {
                    // 平蛇右移
                    int state = encode(r1, c1 + 1, r1, c1);
                    if (validate(r1, c1 + 1, grid) && !visited.contains(state)) {
                        queue.offer(new int[]{r1, c1 + 1, r1, c1});
                        visited.add(state);
                    }
                    if (validate(r1 + 1, c1, grid) && validate(r2 + 1, c2, grid)) {
                        // 平蛇下移
                        state = encode(r1 + 1, c1, r2 + 1, c2);
                        if (!visited.contains(state)) {
                            queue.offer(new int[]{r1 + 1, c1, r2 + 1, c2});
                            visited.add(state);
                        }
                        // 平蛇逆时针旋转90度
                        state = encode(r2 + 1, c2, r2, c2);
                        if (!visited.contains(state)) {
                            queue.offer(new int[]{r2 + 1, c2, r2, c2});
                            visited.add(state);
                        }
                    }
                } else if (c1 == c2 && r1 == r2 + 1) {
                    // 竖蛇下移
                    int state = encode(r1 + 1, c1, r1, c1);
                    if (validate(r1 + 1, c1, grid) && !visited.contains(state)) {
                        queue.offer(new int[]{r1 + 1, c1, r1, c1});
                        visited.add(encode(r1 + 1, c1, r1, c1));
                    }
                    if (validate(r1, c1 + 1, grid) && validate(r2, c2 + 1, grid)) {
                        // 竖蛇右移
                        state = encode(r1, c1 + 1, r2, c2 + 1);
                        if (!visited.contains(state)) {
                            queue.offer(new int[]{r1, c1 + 1, r2, c2 + 1});
                            visited.add(state);
                        }
                        // 竖蛇正时针旋转90度
                        state = encode(r2, c2 + 1, r2, c2);
                        if (!visited.contains(state)) {
                            queue.offer(new int[]{r2, c2 + 1, r2, c2});
                            visited.add(state);
                        }
                    }
                }
            }
            moves++;
        }
        return -1;
    }

    private boolean validate(int x, int y, int[][] grid) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 0;
    }

    private int encode(int x1, int y1, int x2, int y2) {
        int res = (x1 * 100) + y1;
        res = (res * 100) + x2;
        res = (res * 100) + y2;
        return res;
    }
}
