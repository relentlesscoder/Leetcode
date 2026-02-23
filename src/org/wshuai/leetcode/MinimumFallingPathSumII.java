package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/16/2019.
 * #1289 https://leetcode.com/problems/minimum-falling-path-sum-ii/
 */
public class MinimumFallingPathSumII {

	// time O(m * n), space O(1)
	public int minFallingPathSumDP(int[][] grid) {
		// 进一步优化，因为对每一行的计算只依赖上一行的最小值 (如果列数相同则上一行的次最小值)
		// 所以我们只需要记录每一行结束后的最小值及其索引以及次最小值即可。
		int m = grid.length, n = grid[0].length;
		int minSum = Integer.MAX_VALUE, // 上一行结束后的最小路径和
				secondMinSum = Integer.MAX_VALUE, // 上一行结束后的次最小路径和
				minSumIndex = -1; // 上一行结束后的最小路径和的索引
		// 将变量初始化为最后一行的值
		for (int i = 0; i < n; i++) {
			if (grid[m - 1][i] < minSum) {
				secondMinSum = minSum;
				minSum = grid[m - 1][i];
				minSumIndex = i;
			} else if (grid[m - 1][i] < secondMinSum) {
				secondMinSum = grid[m - 1][i];
			}
		}
		// 从倒数第二行开始对每一行进行计算
		for (int i = m - 2; i >= 0; i--) {
			int rowMinSum = Integer.MAX_VALUE, // 从当前行出发的最小路径和
					secondRowMinSum = Integer.MAX_VALUE, // 从当前行出发的次最小路径和
					rowMinSumIndex = -1; // 从当前行出发的最小路径和的索引
			for (int j = 0; j < n; j++) {
				// 1. 如果前一行结束后的最小路径和与当前列不同则从当前格子出发的最小路径和为
				// grid[i][j] + minSum 。
				// 2. 如果前一行结束后的最小路径和与当前列相同则从当前格子出发的最小路径和为
				// grid[i][j] + secondMinSum 。
				int cost = grid[i][j] + (j == minSumIndex ? secondMinSum : minSum);
				if (cost < rowMinSum) {
					secondRowMinSum = rowMinSum;
					rowMinSum = cost;
					rowMinSumIndex = j;
				} else if (cost < secondRowMinSum) {
					secondRowMinSum = cost;
				}
			}
			// 更新变量值
			minSum = rowMinSum;
			secondMinSum = secondRowMinSum;
			minSumIndex = rowMinSumIndex;
		}
		return minSum;
	}

	// time O(m * n^2), space O(n)
	public int minFallingPathSumDPWithArray(int[][] grid) {
		// 空间优化版 DP
		int res = Integer.MAX_VALUE, m = grid.length, n = grid[0].length;
		int[] pre = new int[n];
		Arrays.setAll(pre, i -> grid[m - 1][i]);
		for (int i = m - 2; i >= 0; i--) {
			int[] dp = new int[n];
			for (int j = 0; j < n; j++) {
				int min = Integer.MAX_VALUE;
				for (int k = 0; k < n; k++) {
					if (k == j) {
						continue;
					}
					min = Math.min(min, pre[k]);
				}
				dp[j] = min + grid[i][j];
			}
			pre = dp;
		}
		for (int i = 0; i < n; i++) {
			res = Math.min(res, pre[i]);
		}
		return res;
	}

	// time O(m * n^2), space O(m * n)
	public int minFallingPathSumDPWithGrid(int[][] grid) {
		// 把记忆化搜素翻译成 DP
		int res = Integer.MAX_VALUE, m = grid.length, n = grid[0].length;
		int[][] dp = new int[m][n];
		Arrays.setAll(dp[m - 1], i -> grid[m - 1][i]);
		for (int i = m - 2; i >= 0; i--) {
			for (int j = 0; j < n; j++) {
				int min = Integer.MAX_VALUE;
				for (int k = 0; k < n; k++) {
					if (k == j) {
						continue;
					}
					min = Math.min(min, dp[i + 1][k]);
				}
				dp[i][j] = min + grid[i][j];
			}
		}
		for (int i = 0; i < n; i++) {
			res = Math.min(res, dp[0][i]);
		}
		return res;
	}

    // time O(m * n^2), space O(m * n)
    public int minFallingPathSumDFSWithMemorization(int[][] grid) {
		// 记忆化搜索
        int res = Integer.MAX_VALUE, m = grid.length, n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dfs(0, i, grid, memo));
        }
        return res;
    }

    private int dfs(int i, int j, int[][] grid, int[][] memo) {
        int min = Integer.MAX_VALUE, m = grid.length, n = grid[0].length;
        if (i == m - 1) {
            return grid[i][j];
        }
        if (memo[i][j] != Integer.MIN_VALUE) {
            return memo[i][j];
        }
		// 找到当前格子到下一行所有可行的格子的最小路径和
        for (int k = 0; k < n; k++) {
            if (k == j) { // 非零偏移下降路径中相邻的格子不能在同一列
                continue;
            }
            min = Math.min(min, dfs(i + 1, k, grid, memo));
        }
        return memo[i][j] = min + grid[i][j];
    }
}
