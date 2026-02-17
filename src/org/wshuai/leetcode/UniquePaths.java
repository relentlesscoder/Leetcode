package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/28/2019.
 * #0062 https://leetcode.com/problems/unique-paths/
 */
public class UniquePaths {

    // time O(m * n), space O(n)
    public int uniquePaths(int m, int n) {
		// 空间优化版 DP
        int[] pre = new int[n + 1];
        pre[1] = 1;
        for (int i = 1; i <= m; i++) {
            int[] dp = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                dp[j] = pre[j] + dp[j - 1];
            }
            pre = dp;
        }
        return pre[n];
    }

    // time O(m * n), space O(m * n)
    public int uniquePathsDPWithGrid(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        dp[0][1] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    // time O(m * n), space O(m * n)
    public int uniquePathsDFSWithMemorization(int m, int n) {
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(m - 1, n - 1, memo);
    }

    private int dfs(int i, int j, int[][] memo) {
        if (i < 0 || j < 0) {
            return 0;
        }
        if (i == 0 && j == 0) {
            return 1;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // 到达 grid[i][j] 的路径总和为从左 (i, j - 1) 或者从右来 (i - 1, j) 的路径
        // 相加, 因此可以分解为更小的子问题。
        return memo[i][j] = dfs(i - 1, j, memo) + dfs(i, j - 1, memo);
    }
}
