package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 02/24/2026.
 * #3665 https://leetcode.com/problems/twisted-mirror-path-count/
 */
public class TwistedMirrorPathCount {

    private static final int MOD = (int) 1e9 + 7;

    // time O(m * n), space O(n)
    public int uniquePathsDP(int[][] grid) {
        // 空间优化版 DP
        int m = grid.length, n = grid[0].length;
        int[][] pre = new int[n + 1][2];
        pre[n - 1][0] = 1;
        for (int i = m - 1; i >= 0; i--) {
            int[][] dp = new int[n + 1][2];
            for (int j = n - 1; j >= 0; j--) {
                for (int d = 0; d <= 1; d++) {
                    if (grid[i][j] == 0) {
                        dp[j][d] = (pre[j][0] + dp[j + 1][1]) % MOD;
                    } else if (d == 0) {
                        dp[j][d] = dp[j + 1][1];
                    } else {
                        dp[j][d] = pre[j][0];
                    }
                }
            }
            pre = dp;
        }
        return pre[0][0];
    }

    // time O(m * n), space O(m * n)
    public int uniquePathsDPWithGrid(int[][] grid) {
        // 将记忆化搜索翻译成 DP
        int m = grid.length, n = grid[0].length;
        int[][][] dp = new int[m + 1][n + 1][2];
        dp[m][n - 1][0] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                for (int d = 0; d <= 1; d++) {
                    if (grid[i][j] == 0) {
                        dp[i][j][d] = (dp[i + 1][j][0] + dp[i][j + 1][1]) % MOD;
                    } else if (d == 0) {
                        dp[i][j][d] = dp[i][j + 1][1];
                    } else {
                        dp[i][j][d] = dp[i + 1][j][0];
                    }
                }
            }
        }
        return dp[0][0][0];
    }

    // time O(m * n), space O(m * n)
    public int uniquePaths(int[][] grid) {
        // 记忆化搜索
        int m = grid.length, n = grid[0].length;
        int[][][] memo = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j][0] = memo[i][j][1] = -1;
            }
        }
        return dfs(0, 0, 0, grid, memo);
    }

    private int dfs(int i, int j, int d, int[][] grid, int[][][] memo) {
        // d: 下 0 右 1
        int m = grid.length, n = grid[0].length;
        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        if (i == m || j == n) {
            return 0;
        }
        if (memo[i][j][d] != -1) {
            return memo[i][j][d];
        }
        if (grid[i][j] == 0) { // 当前方格为空，则右和下的方格都可走
            return memo[i][j][d] = (dfs(i + 1, j, 0, grid, memo)
                    + dfs(i, j + 1, 1, grid, memo)) % MOD;
        } else if (d == 0) { // 向下移动进入镜子则反射到右边的方格
            return memo[i][j][d] = dfs(i, j + 1, 1, grid, memo);
        } else { // 向右移动进入镜子则反射到下边的方格
            return memo[i][j][d] = dfs(i + 1, j, 0, grid, memo);
        }
    }
}
