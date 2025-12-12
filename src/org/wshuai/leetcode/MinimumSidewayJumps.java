package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 09/26/2023.
 * #1824 https://leetcode.com/problems/minimum-sideway-jumps/
 */
public class MinimumSidewayJumps {

    // time O(n), space O(1)
    public int minSideJumps(int[] obstacles) {
        int n = obstacles.length;
        // dp[i] denotes the cost of lane i + 1
        int[] dp = new int[]{1, 0, 1};
        for (int i = 1; i < n; i++) {
            if (obstacles[i] > 0) {
                // Overrides cost of the lane to max if there is an obstacle
                // in lane, meaning we can't proceed without changing the lane.
                dp[obstacles[i] - 1] = 1_000_000;
            }
            for (int j = 0; j < 3; j++) {
                // Special case: obstacles[i] = 0, maintain the current cost.
                if (j + 1 != obstacles[i]) {
                    // for lanes without obstacle, add the jump cost to the min between
                    // the costs from the other two lanes.
                    dp[j] = Math.min(dp[j], Math.min(dp[(j + 1) % 3], dp[(j + 2) % 3]) + 1);
                }
            }
        }
        return Math.min(dp[0], Math.min(dp[1], dp[2]));
    }

    // time O(n), space O(n)
    public int minSideJumpsBFS(int[] obstacles) {
        int n = obstacles.length;
        int[][] dist = new int[3][n];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[1][0] = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offerFirst(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] curr = queue.pollFirst();
            int x = curr[0], y = curr[1];
            if (y == n - 1) {
                return dist[x][y];
            }
            if (obstacles[y + 1] != x + 1) {
                dist[x][y + 1] = dist[x][y];
                queue.offerFirst(new int[]{x, y + 1});
            } else {
                for (int i = 1; i <= 3; i++) {
                    if (i == obstacles[y] || x == i - 1) {
                        continue;
                    }
                    if (dist[i - 1][y] > dist[x][y] + 1) {
                        dist[i - 1][y] = dist[x][y] + 1;
                        queue.offerLast(new int[]{i - 1, y});
                    }
                }
            }
        }
        return -1;
    }
}
