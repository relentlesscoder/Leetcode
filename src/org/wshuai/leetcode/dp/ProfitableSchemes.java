package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 12/04/2019.
 * #0879 https://leetcode.com/problems/profitable-schemes/
 */
public class ProfitableSchemes {

	private static final int MOD = (int) 1e9 + 7;

	// time O(m * n * p), space O(n * p)
	public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
		// 空间优化版 DP
		int res = 0, m = group.length;
		int[][] dp = new int[n + 1][minProfit + 1];
		dp[0][0] = 1;
		for (int i = 0; i < m; i++) {
			for (int j = n; j >= 0; j--) {
				for (int k = minProfit; k >= 0; k--) {
					if (j >= group[i]) {
						dp[j][k] = (dp[j][k] + dp[j - group[i]][Math.max(0, k - profit[i])]) % MOD;
					} else {
						dp[j][k] = dp[j][k];
					}
				}
			}
		}
		// 计算结果时，j 从 0 到 n 都要累加，因为最多只能有 n 人参加，但不一定要满 n 人。
		for (int i = 0; i <= n; i++) {
			res = (res + dp[i][minProfit]) % MOD;
		}
		return res;
	}

	// time O(m * n * p), space O(m * n * p)
	public int profitableSchemesDPWithGrid(int n, int minProfit, int[] group, int[] profit) {
		// 将记忆化搜索翻译成 DP
		int res = 0, m = group.length;
		int[][][] dp = new int[m + 1][n + 1][minProfit + 1];
		dp[0][0][0] = 1;
		for (int i = 0; i < m; i++) {
			for (int j = n; j >= 0; j--) {
				for (int k = minProfit; k >= 0; k--) {
					if (j >= group[i]) {
						dp[i + 1][j][k] = (dp[i][j][k] + dp[i][j - group[i]][Math.max(0, k - profit[i])]) % MOD;
					} else {
						dp[i + 1][j][k] = dp[i][j][k];
					}
				}
			}
		}
		// 计算结果时，j 从 0 到 n 都要累加，因为最多只能有 n 人参加，但不一定要满 n 人。
		for (int i = 0; i <= n; i++) {
			res = (res + dp[m][i][minProfit]) % MOD;
		}
		return res;
	}

	// time O(m * n * p), space O(m * n * p)
	public int profitableSchemesDFSWithMemorization(int n, int minProfit, int[] group, int[] profit) {
		// 记忆化搜索
		int m = group.length;
		// 优化1: 先把输入数据按照 group 升序排序，后续在 dfs 过程中遇到 w < grid[i][0]
		// 就可以直接返回了。
		int[][] sorted = new int[m][2];
		Arrays.setAll(sorted, i -> new int[] { group[i], profit[i] });
		Arrays.sort(sorted, (a, b) -> a[0] - b[0]);
		int[][][] memo = new int[m][n + 1][minProfit + 1];
		for (int[][] matrix : memo) {
			for (int[] row : matrix) {
				Arrays.fill(row, -1);
			}
		}
		return dfs(0, n, minProfit, sorted, memo);
	}

	private int dfs(int i, int w, int p, int[][] grid, int[][][] memo) {
		if (i == grid.length || w < grid[i][0]) {
			return p == 0 ? 1 : 0;
		}
		if (memo[i][w][p] != -1) {
			return memo[i][w][p];
		}
		// 优化2: 将 p 限制在 [0, minProfit] 之间，超过 minProfit 的情况都当成 minProfit
		// 来处理，这样可以减少状态数量。
		return memo[i][w][p] = (dfs(i + 1, w, p, grid, memo)
				+ dfs(i + 1, w - grid[i][0], Math.max(0, p - grid[i][1]), grid, memo)) % MOD;
	}
}
