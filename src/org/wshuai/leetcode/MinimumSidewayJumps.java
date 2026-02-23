package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 09/26/2023.
 * #1824 https://leetcode.com/problems/minimum-sideway-jumps/
 */
public class MinimumSidewayJumps {

    private static final int MAX = (int) 1e6;

    // time O(n), space O(1)
    public int minSideJumpsDP(int[] obstacles) {
        // 空间优化版 DP
        int n = obstacles.length;
        int[] pre = new int[3];
        for (int i = n - 2; i >= 0; i--) {
            int[] dp = new int[3];
            for (int j = 0; j < 3; j++) {
                if (obstacles[i] == j + 1) {
                    dp[j] = MAX;
                    continue;
                }
                dp[j] = pre[j];
                if (obstacles[i] != (j + 1) % 3 + 1) {
                    dp[j] = Math.min(dp[j], 1 + pre[(j + 1) % 3]);
                }
                if (obstacles[i] != (j + 2) % 3 + 1) {
                    dp[j] = Math.min(dp[j], 1 + pre[(j + 2) % 3]);
                }
            }
            pre = dp;
        }
        return pre[1];
    }

    // time O(n), space O(n)
    public int minSideJumpsDPWithGrid(int[] obstacles) {
        // 把记忆化搜索翻译成 DP
        int n = obstacles.length;
        int[][] dp = new int[3][n];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                if (obstacles[i] == j + 1) {
                    dp[j][i] = MAX;
                    continue;
                }
                dp[j][i] = dp[j][i + 1];
                if (obstacles[i] != (j + 1) % 3 + 1) {
                    dp[j][i] = Math.min(dp[j][i], 1 + dp[(j + 1) % 3][i + 1]);
                }
                if (obstacles[i] != (j + 2) % 3 + 1) {
                    dp[j][i] = Math.min(dp[j][i], 1 + dp[(j + 2) % 3][i + 1]);
                }
            }
        }
        return dp[1][0];
    }

    // time O(n), space O(n)
    public int minSideJumpsDFSWithMemorization(int[] obstacles) {
        // 记忆化搜索
        int n = obstacles.length;
        int[][] memo = new int[3][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(1, 0, obstacles, memo);
    }

    private int dfs(int x, int y, int[] obstacles, int[][] memo) {
        int n = obstacles.length;
        // 到达最后一列返回 0
        if (y == n - 1) {
            return 0;
        }
        // 如果当前格子有障碍物则直接返回最大值 - 不能走
        if (obstacles[y] == x + 1) {
            return MAX;
        }
        if (memo[x][y] != -1) {
            return memo[x][y];
        }
        // 直接去下一列同一行的方格 - 不用横跳
        int res = dfs(x, y + 1, obstacles, memo);
        // 如果同一列另两个方格没有障碍物也可以横跳一步去下一列对应行的方格
        if (obstacles[y] != (x + 1) % 3 + 1) {
            res = Math.min(res, 1 + dfs((x + 1) % 3, y + 1, obstacles, memo));
        }
        if (obstacles[y] != (x + 2) % 3 + 1) {
            res = Math.min(res, 1 + dfs((x + 2) % 3, y + 1, obstacles, memo));
        }
        return memo[x][y] = res;
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
