package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 06/07/2017.
 * #0174 https://leetcode.com/problems/dungeon-game/
 */
public class DungeonGame {

    // time O(m * n), space O(m * n)
    public int calculateMinimumHPDPWithArray(int[][] dungeon) {
        // 空间优化版 DP
        int m = dungeon.length, n = dungeon[0].length;
        int[] pre = new int[n + 1];
        Arrays.fill(pre, Integer.MAX_VALUE);
        for (int i = m - 1; i >= 0; i--) {
            int[] dp = new int[n + 1];
            dp[n] = Integer.MAX_VALUE;
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    dp[j] = dungeon[i][j] >= 0 ? 0 : -dungeon[i][j];
                    continue;
                }
                dp[j] = Math.max(0, Math.min(pre[j], dp[j + 1]) - dungeon[i][j]);
            }
            pre = dp;
        }
        return pre[0] + 1;
    }

    // time O(m * n), space O(m * n)
    public int calculateMinimumHPDPWithGrid(int[][] dungeon) {
        // 将记忆化搜索翻译成 DP
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];
        Arrays.fill(dp[m], Integer.MAX_VALUE);
        for (int i = m - 1; i >= 0; i--) {
            dp[i][n] = Integer.MAX_VALUE;
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    dp[i][j] = dungeon[i][j] >= 0 ? 0 : -dungeon[i][j];
                    continue;
                }
                dp[i][j] = Math.max(0, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }
        }
        return dp[0][0] + 1;
    }

    // time O(m * n), space O(m * n)
    public int calculateMinimumHPDFSWithMemorization(int[][] dungeon) {
        // 记忆化搜索
        int m = dungeon.length, n = dungeon[0].length;
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        // 深度搜索计算骑士从 dungeon[0,0] 开始所需最低血量
        return dfs(0, 0, dungeon, memo) + 1; // 注意最后答案要加一
    }

    private int dfs(int i, int j, int[][] grid, int[][] memo) {
        int m = grid.length, n = grid[0].length;
        if (i == m - 1 && j == n - 1) {
            return grid[i][j] >= 0 ? 0 : -grid[i][j];
        }
        if (i == m || j == n) {
            return Integer.MAX_VALUE;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // 骑士在格子 grid[i, j] 所需要的最低血量等于向右或者向下走的最低血量
        // 的较小值扣除/加上当前格子耗费/补充的血量 - 注意如果这个值为非负则返
        // 回 0 以表示骑士不需要任何最低血量。
        return memo[i][j] = Math.max(0, Math.min(dfs(i, j + 1, grid, memo),
                dfs(i + 1, j, grid, memo)) - grid[i][j]);
    }
}
