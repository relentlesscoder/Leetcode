package org.wshuai.leetcode;

/**
 * Created by Wei on 02/21/2026.
 * #3418 https://leetcode.com/problems/maximum-amount-of-money-robot-can-earn/
 */
public class MaximumAmountOfMoneyRobotCanEarn {

    // time O(m * n), space O(n)
    public int maximumAmount(int[][] coins) {
        // 空间优化版 DP
        int m = coins.length, n = coins[0].length;
        int[][] pre = new int[n + 1][3];
        for (int i = 0; i <= n; i++) {
            pre[i][0] = pre[i][1] = pre[i][2] = Integer.MIN_VALUE / 2;
        }
        pre[n - 1][0] = pre[n - 1][1] = pre[n - 1][2] = 0;
        for (int i = m - 1; i >= 0; i--) {
            int[][] dp = new int[n + 1][3];
            dp[n][0] = dp[n][1] = dp[n][2] = Integer.MIN_VALUE / 2;
            for (int j = n - 1; j >= 0; j--) {
                for (int t = 0; t < 3; t++) {
                    int c = coins[i][j] + Math.max(pre[j][t], dp[j + 1][t]);
                    if (coins[i][j] < 0 && t > 0) {
                        c = Math.max(c, Math.max(pre[j][t - 1], dp[j + 1][t - 1]));
                    }
                    dp[j][t] = c;
                }
            }
            pre = dp;
        }
        return pre[0][2];
    }

    // time O(m * n), space O(m * n)
    public int maximumAmountDPWithGrid(int[][] coins) {
        // 把记忆化搜索翻译成 DP
        int m = coins.length, n = coins[0].length;
        // 多加一行一列处理出界的情况以便于计算
        int[][][] dp = new int[m + 1][n + 1][3];
        // 将最后一行设为出界
        for (int i = 0; i <= n; i++) {
            dp[m][i][0] = dp[m][i][1] = dp[m][i][2] = Integer.MIN_VALUE / 2;
        }
        // 将 dp[m - 1, n - 1] 下面 (或者右边) 的格子预设为 0 便于计算
        dp[m][n - 1][0] = dp[m][n - 1][1] = dp[m][n - 1][2] = 0;
        for (int i = m - 1; i >= 0; i--) {
            // 将每一行的最后一列的方格设为出界
            dp[i][n][0] = dp[i][n][1] = dp[i][n][2] = Integer.MIN_VALUE / 2;
            for (int j = n - 1; j >= 0; j--) {
                for (int t = 0; t < 3; t++) {
                    // 翻译记忆化搜索中的逻辑
                    int c = coins[i][j] + Math.max(dp[i + 1][j][t], dp[i][j + 1][t]);
                    if (coins[i][j] < 0 && t > 0) {
                        c = Math.max(c, Math.max(dp[i + 1][j][t - 1], dp[i][j + 1][t - 1]));
                    }
                    dp[i][j][t] = c;
                }
            }
        }
        return dp[0][0][2];
    }

    // time O(m * n), space O(m * n)
    public int maximumAmountDFSWithMemorization(int[][] coins) {
        // 记忆化搜索
        int m = coins.length, n = coins[0].length;
        int[][][] memo = new int[m][n][3];
        for (int i = 0; i < m; i++) {
            for (int[] x : memo[i]) {
                x[0] = x[1] = x[2] = Integer.MIN_VALUE;
            }
        }
        return dfs(0, 0, 2, coins, memo);
    }

    private int dfs(int i, int j, int t, int[][] coins, int[][][] memo) {
        int m = coins.length, n = coins[0].length;
        if (i == m - 1 && j == n - 1) {
            // 到达目标方格，三种情况:
            //   1. 目标方格金币非负则取之
            //   2. 目标方格金币为负且感化数量为 0 亦取之 - 注意取之于负数就相当于被打劫了
            //   3. 目标方格金币为负且感化数量不为 0 则不取
            return (coins[i][j] >= 0 || t == 0) ? coins[i][j] : 0;
        }
        // 出界则不合法
        if (i == m || j == n) {
            return Integer.MIN_VALUE / 2;
        }
        if (memo[i][j][t] != Integer.MIN_VALUE) {
            return memo[i][j][t];
        }
        // 三种情况:
        //   1. 目标方格金币非负则取之
        //   2. 目标方格金币为负且感化数量为 0 亦取之 - 注意取之于负数就相当于被打劫了
        // 注意这前两种情况处理是一样的
        int res = coins[i][j] + Math.max(dfs(i + 1, j, t, coins, memo),
                dfs(i, j + 1, t, coins, memo));
        //   3. 目标方格金币为负且感化数量不为 0 则可选择使用一次感化而不取
        if (coins[i][j] < 0 && t > 0) {
            res = Math.max(res, Math.max(dfs(i + 1, j, t - 1, coins, memo),
                    dfs(i, j + 1, t - 1, coins, memo)));
        }
        return memo[i][j][t] = res;
    }
}
