package org.wshuai.leetcode;

/**
 * Created by Wei on 01/24/2024.
 * #2328 https://leetcode.com/problems/number-of-increasing-paths-in-a-grid/
 */
public class NumberOfIncreasingPathsInAGrid {

    private static final int MOD = (int) 1e9 + 7;
    private static final int[] DIRS = new int[] {0, -1, 0, 1, 0};

    public int countPathsDFSWithMemorization(int[][] grid) {
        // 记忆化搜索
        // #0329 相似题
        int res = 0, m = grid.length, n = grid[0].length;
        int[][] memo = new int[m][n]; // memo[i][j] 表示以 grid[i][j] 开头的递增路径的数量
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (memo[i][j] != 0) {
                    res = (res + memo[i][j]) % MOD;
                    continue;
                }
                res = (res + dfs(i, j, grid, memo)) % MOD;
            }
        }
        return res;
    }

    private int dfs(int i, int j, int[][] grid, int[][] memo) {
        int m = grid.length, n = grid[0].length;
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        memo[i][j] = 1; // 注意方格本身也算一条合法路径
        for (int d = 0; d < 4; d++) {
            int x = i + DIRS[d], y = j + DIRS[d + 1];
            // 如果相邻的格子比方格的值大，则所有以相邻格子开始的递增路径也可以以当前格子开始。
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > grid[i][j]) {
                memo[i][j] = (memo[i][j] + dfs(x, y, grid, memo)) % MOD;
            }
        }
        return memo[i][j];
    }
}
