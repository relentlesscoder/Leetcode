package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 08/17/2025.
 * #3341 https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i/
 */
public class FindMinimumTimeToReachLastRoomI {

    private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};

    // time O(m * n * log(m * n)), space O(m * n)
    public int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length, n = moveTime[0].length;
        PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        minQueue.offer(new int[]{0, 0, 0});
        while (!minQueue.isEmpty()) {
            int[] curr = minQueue.poll();
            if (curr[0] == m - 1 && curr[1] == n - 1) {
                return curr[2];
            }
            for (int k = 0; k < 4; k++) {
                int x = curr[0] + DIRS[k], y = curr[1] + DIRS[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && moveTime[x][y] >= 0) {
                    minQueue.offer(new int[]{x, y, Math.max(curr[2], moveTime[x][y]) + 1});
                    moveTime[x][y] = -1;
                }
            }
        }
        return -1;
    }
}
