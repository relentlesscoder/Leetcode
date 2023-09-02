package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/01/2023.
 * #2684 https://leetcode.com/problems/maximum-number-of-moves-in-a-grid/description/
 */
public class MaximumNumberOfMovesInAGrid {
    private int[][] dirs = new int[][]{{-1, 1}, {0, 1}, {1, 1}};
    private int[][] moves;
    private int row, col;

    // time O(m*n), space O(m*n)
    public int maxMoves(int[][] grid) {
        int res = 0;
        row = grid.length;
        col = grid[0].length;
        moves = new int[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(moves[i], -1);
        }
        for (int i = 0; i < row; i++) {
            res = Math.max(res, moveFrom(grid, i, 0));
        }
        return res;
    }

    private int moveFrom(int[][] grid, int i, int j) {
        if (moves[i][j] != -1) {
            return moves[i][j];
        }
        int res = 0;
        for (int k = 0; k < 3; k++) {
            int r = i + dirs[k][0], c = j + dirs[k][1];
            if (r < 0 || r >= row || c >= col || grid[r][c] <= grid[i][j]) {
                continue;
            }
            res = Math.max(res, 1 + moveFrom(grid, r, c));
        }
        moves[i][j] = res;
        return res;
    }

    // time O(m*n), space O(m*n)
    public int maxMovesDP(int[][] grid) {
        int res = 0, row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < col; j++) {
            for (int i = 0; i < row; i++) {
                if (i - 1 >= 0 && grid[i][j] > grid[i - 1][j - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1]);
                }
                if (grid[i][j] > grid[i][j - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }
                if (i + 1 < row && grid[i][j] > grid[i + 1][j - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1]);
                }
                if (dp[i][j] > 0) {
                    dp[i][j]++;
                }
                res = Math.max(dp[i][j] - 1, res);
            }
        }
        return res;
    }
}
