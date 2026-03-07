package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 09/12/2019.
 * #0741 https://leetcode.com/problems/cherry-pickup/
 */
public class CherryPickup {

	// time O(n^3), space O(n^2)
	public int cherryPickup(int[][] grid) {
		// 空间优化版 DP
		int n = grid.length, m = 2 * n - 1;
		int[][] pre = new int[n + 1][n + 1];
		for (int[] row : pre) {
			Arrays.fill(row, -1_000);
		}
		pre[1][1] = grid[0][0];
		for (int s = 1; s < m; s++) {
			int[][] dp = new int[n + 1][n + 1];
			for (int[] row : dp) {
				Arrays.fill(row, -1_000);
			}
			for (int j1 = Math.max(s - n + 1, 0); j1 <= Math.min(s, n - 1); j1++) {
				if (grid[s - j1][j1] < 0) {
					continue;
				}
				for (int j2 = Math.max(s - n + 1, 0); j2 <= Math.min(s, n - 1); j2++) {
					if (grid[s - j2][j2] < 0) {
						continue;
					}
					int max = Math.max(Math.max(pre[j1 + 1][j2 + 1], pre[j1][j2]),
							Math.max(pre[j1][j2 + 1], pre[j1 + 1][j2]));
					dp[j1 + 1][j2 + 1] = max + (j1 == j2 ? grid[s - j1][j1] : grid[s - j1][j1] + grid[s - j2][j2]);
				}
			}
			pre = dp;
		}
		return Math.max(pre[n][n], 0);
	}

    // time O(n^3), space O(n^3)
    public int cherryPickupDPWithGrid(int[][] grid) {
        // 把记忆化搜索翻译成 DP
        int n = grid.length, m = 2 * n - 1;
        int[][][] dp = new int[m][n + 1][n + 1];
        for (int[][] matrix : dp) {
            for (int[] row : matrix) {
                Arrays.fill(row, -1_000);
            }
        }
        dp[0][1][1] = grid[0][0];
        // 需要注意 j1 和 j2 的循环范围。
        // 由于 i1 + j1 = s 且 0 <= i1 <= n − 1 且 0 <= j1 <= n − 1
        //   s - j1 >= 0       -> j1 <= s
        //   s - j1 <= n - 1   -> j1 >= s - n - 1
        //   j1 >= 0           -> j1 >= 0
        //   j1 <= n - 1       -> j1 <= n - 1
        // 联立得 max(s − n + 1, 0) <= j1 <= min(s, n − 1), 对于 j2 也同理。
        for (int s = 1; s < m; s++) {
            for (int j1 = Math.max(s - n + 1, 0); j1 <= Math.min(s, n - 1); j1++) {
                if (grid[s - j1][j1] < 0) {
                    continue;
                }
                for (int j2 = Math.max(s - n + 1, 0); j2 <= Math.min(s, n - 1); j2++) {
                    if (grid[s - j2][j2] < 0) {
                        continue;
                    }
                    int max = Math.max(Math.max(dp[s - 1][j1 + 1][j2 + 1], dp[s - 1][j1][j2]),
                            Math.max(dp[s - 1][j1][j2 + 1], dp[s - 1][j1 + 1][j2]));
                    dp[s][j1 + 1][j2 + 1] = max + (j1 == j2 ? grid[s - j1][j1] : grid[s - j1][j1] + grid[s - j2][j2]);
                }
            }
        }
        return Math.max(dp[m - 1][n][n], 0);
    }

    // time O(n^3), space O(n^3)
    public int cherryPickupDFSWithMemorization(int[][] grid) {
        // 记忆化搜索 - 将问题转化为两个人从出发点一起出发最后同时到达右下角格子所能摘到
        // 的樱桃的最大数量。
        int n = grid.length;
        // memo[i][j][k] i: 当前步数， j: 第一个人的列坐标， k: 第二个人的列坐标
        int[][][] memo = new int[2 * n - 1][n][n];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return Math.max(dfs(2 * n - 2, n - 1, n - 1, grid, memo), 0);
    }

    private int dfs(int s, int j1, int j2, int[][] grid, int[][][] memo) {
        if (s == 0) { // 回到出发点
            // j1 == 0 && j2 == 0 && s - j1 == 0 && s - j2 == 0
            return grid[0][0];
        }
        // 越界或者遇到荆棘
        if (j1 < 0 || j2 < 0 || s - j1 < 0 || s - j2 < 0
                || grid[s - j1][j1] == -1 || grid[s - j2][j2] == -1) {
            return -1_000;
        }
        if (memo[s][j1][j2] != -1) {
            return memo[s][j1][j2];
        }
        int max = Math.max(Math.max(dfs(s - 1, j1, j2, grid, memo), // 两个人都是从上面格子过来
                        dfs(s - 1, j1 - 1, j2 - 1, grid, memo)), // 两个人都是从左边格子过来
                Math.max(dfs(s - 1, j1, j2 - 1, grid, memo), // 上和左
                        dfs(s - 1, j1 - 1, j2, grid, memo))); // 左和上
        //注意如果恰好走到同一个格子则最多只能摘到一个樱桃
        return memo[s][j1][j2] = max + (j1 == j2 ? grid[s - j1][j1] : grid[s - j1][j1] + grid[s - j2][j2]);
    }
}
