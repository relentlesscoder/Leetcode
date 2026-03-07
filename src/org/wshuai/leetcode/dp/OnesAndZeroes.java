package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 02/25/2017.
 * #0474 https://leetcode.com/problems/ones-and-zeroes/
 */
public class OnesAndZeroes {

    // time O(m * n * l), space O(m * n)
    public int findMaxForm(String[] strs, int m, int n) {
		// 空间优化版 DP
        int l = strs.length;
        int[][] grid = new int[l][2];
        for (int i = 0; i < l; i++) {
            for (char c : strs[i].toCharArray()) {
                grid[i][c - '0']++;
            }
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = l - 1; i >= 0; i--) {
			// 注意要从右下往左上计算，则 s 左上的值还是上一行的计算结果
            for (int j = m; j >= 0; j--) {
                for (int k = n; k >= 0; k--) {
                    if (j >= grid[i][0] && k >= grid[i][1]) {
                        dp[j][k] = Math.max(dp[j][k],
                                1 + dp[j - grid[i][0]][k - grid[i][1]]);
                    }
                }
            }
        }
        return dp[m][n];
    }

    // time O(m * n * l), space O(m * n * l)
    public int findMaxFormDPWithGrid(String[] strs, int m, int n) {
        // 把记忆化搜索翻译成 DP
        int l = strs.length;
        int[][] grid = new int[l][2];
        for (int i = 0; i < l; i++) {
            for (char c : strs[i].toCharArray()) {
                grid[i][c - '0']++;
            }
        }
        int[][][] dp = new int[l + 1][m + 1][n + 1];
        for (int i = l - 1; i >= 0; i--) {
            for (int j = m; j >= 0; j--) {
                for (int k = n; k >= 0; k--) {
                    if (j < grid[i][0] || k < grid[i][1]) {
                        dp[i][j][k] = dp[i + 1][j][k];
                    } else {
                        dp[i][j][k] = Math.max(dp[i + 1][j][k],
                                1 + dp[i + 1][j - grid[i][0]][k - grid[i][1]]);
                    }
                }
            }
        }
        return dp[0][m][n];
    }

    // time O(m * n * l), space O(m * n * l)
    public int findMaxFormDFSWithMemorization(String[] strs, int m, int n) {
        // 记忆化搜索 - 0/1 背包
        int l = strs.length;
        int[][] grid = new int[l][2];
        for (int i = 0; i < l; i++) {
            for (char c : strs[i].toCharArray()) {
                grid[i][c - '0']++;
            }
        }
        int[][][] memo = new int[l][m + 1][n + 1];
        for (int[][] matrix : memo) {
            for (int[] row : matrix) {
                Arrays.fill(row, -1);
            }
        }
        return dfs(0, m, n, grid, memo);
    }

    private int dfs(int i, int m, int n, int[][] grid, int[][][] memo) {
        int l = grid.length;
        if (i == l) {
            return 0;
        }
        if (memo[i][m][n] != -1) {
            return memo[i][m][n];
        }
        if (m < grid[i][0] || n < grid[i][1]) {
            // 不能选
            return memo[i][m][n] = dfs(i + 1, m, n, grid, memo);
        } else {
            // 选或者不选
            return memo[i][m][n] = Math.max(dfs(i + 1, m, n, grid, memo),
                    1 + dfs(i + 1, m - grid[i][0], n - grid[i][1], grid, memo));
        }
    }
}
