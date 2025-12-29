package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/21/2019.
 * #0909 https://leetcode.com/problems/snakes-and-ladders/
 */
public class SnakesAndLadders {

    // time O(n * n), space O(n * n)
    public int snakesAndLadders(int[][] board) {
        int times = 0, n = board.length, sq = n * n;
        boolean[] visited = new boolean[sq + 1];
        Deque<int[]> queue = new ArrayDeque<>();
        int[] start = getPos(1, n);
        queue.offer(start);
        visited[1] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                int v = curr[2];
                for (int i = v + 1; i <= Math.min(sq, v + 6); i++) {
                    int[] next = getPos(i, n);
                    if (board[next[0]][next[1]] != -1) {
                        next = getPos(board[next[0]][next[1]], n);
                    }
                    if (next[2] == sq) {
                        return times + 1;
                    }
                    if (!visited[next[2]]) {
                        queue.offer(next);
                        visited[next[2]] = true;
                    }
                }
            }
            times++;
        }
        return -1;
    }

    private int[] getPos(int x, int n) {
        int d = (x - 1) / n;
        int m = (x - 1) % n;
        int r = n - 1 - d;
        int c = d % 2 == 1 ? n - 1 - m : m;
        return new int[]{r, c, x};
    }
}
