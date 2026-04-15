package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 04/14/2026.
 * #3573
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-v/
 */
public class BestTimeToBuyAndSellStockV {

	// time O(n * k), space O(k)
	public long maximumProfitDP(int[] prices, int k) {
		// 核心思路: 状态机DP, 三维状态(天数, 交易次数, 持仓状态)压缩掉天数维度
		// pre[j][s]: 前一天已用j次交易, 状态s(0=空仓, 1=做多, 2=做空)的最大利润
		int n = prices.length;
		long[][] pre = new long[k + 1][3];
		for (int i = 0; i <= k; i++) {
			pre[i][1] = pre[i][2] = Long.MIN_VALUE / 2; // 做多/做空初始不合法
		}
		for (int i = 0; i < n; i++) {
			long[][] dp = new long[k + 1][3];
			for (int j = 0; j < k; j++) {
				// 做多: 继续持有 或 买入开仓(从j次空仓转移, 消耗一次交易)
				dp[j + 1][1] = Math.max(pre[j + 1][1], pre[j][0] - prices[i]);
				// 做空: 继续做空 或 卖空开仓(从j次空仓转移, 消耗一次交易)
				dp[j + 1][2] = Math.max(pre[j + 1][2], pre[j][0] + prices[i]);
				// 空仓: 保持空仓 或 买回平空仓(付prices[i]) 或 卖出平多仓(收prices[i])
				dp[j + 1][0] = Math.max(pre[j + 1][0], Math.max(pre[j + 1][2] - prices[i], pre[j + 1][1] + prices[i]));
			}
			pre = dp;
		}
		return pre[k][0];
	}

	// time O(n * k), space O(n * k)
	public long maximumProfitDPWithGrid(int[] prices, int k) {
		// 核心思路: 与压缩版相同, 但保留完整三维数组
		// dp[i][j][s]: 前i天已用j次交易, 状态s(0=空仓, 1=做多, 2=做空)的最大利润
		int n = prices.length;
		long[][][] dp = new long[n + 1][k + 1][3];
		for (int i = 0; i <= k; i++) {
			dp[0][i][1] = dp[0][i][2] = Long.MIN_VALUE / 2; // 第0天做多/做空不合法
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k; j++) {
				// 做多: 继续持有 或 买入开仓(消耗一次交易)
				dp[i + 1][j + 1][1] = Math.max(dp[i][j + 1][1], dp[i][j][0] - prices[i]);
				// 做空: 继续做空 或 卖空开仓(消耗一次交易)
				dp[i + 1][j + 1][2] = Math.max(dp[i][j + 1][2], dp[i][j][0] + prices[i]);
				// 空仓: 保持空仓 或 买回平空仓 或 卖出平多仓
				dp[i + 1][j + 1][0] = Math.max(dp[i][j + 1][0],
						Math.max(dp[i][j + 1][2] - prices[i], dp[i][j + 1][1] + prices[i]));
			}
		}
		return dp[n][k][0];
	}

	// time O(n * k), space O(n * k)
	public long maximumProfitDFSWithMemorization(int[] prices, int k) {
		// 核心思路: 状态机DP, 支持做多(低买高卖)和做空(高卖低买)两种交易方式
		// 三个状态: k=0空仓, k=1持有(做多), k=2做空. 每次开仓消耗一次交易机会
		int n = prices.length;
		// memo[i][j][k]: 第i天剩余j+1次交易机会, 状态k的最大利润
		long[][][] memo = new long[n][k][3];
		for (long[][] matrix : memo) {
			for (long[] row : matrix) {
				Arrays.fill(row, Long.MIN_VALUE);
			}
		}
		return dfs(n - 1, k - 1, 0, prices, memo);
	}

	// i: 当前天数, j: 剩余交易次数-1, k: 0=空仓, 1=持有(做多), 2=做空
	private long dfs(int i, int j, int k, int[] prices, long[][][] memo) {
		if (i == -1) {
			// 第0天之前: 空仓利润0, 持有/做空不合法
			return k == 0 ? 0L : Long.MIN_VALUE / 2;
		}
		if (j == -1) {
			// 交易次数用完, 无法再开仓, 利润为0
			return 0L;
		}
		if (memo[i][j][k] != Long.MIN_VALUE) {
			return memo[i][j][k];
		}
		if (k == 1) {
			// 持有(做多): 继续持有 或 买入开仓(消耗一次交易, 付出prices[i])
			return memo[i][j][k] = Math.max(dfs(i - 1, j, 1, prices, memo),
					dfs(i - 1, j - 1, 0, prices, memo) - prices[i]);
		} else if (k == 2) {
			// 做空: 继续做空 或 卖空开仓(消耗一次交易, 收入prices[i])
			return memo[i][j][k] = Math.max(dfs(i - 1, j, 2, prices, memo),
					dfs(i - 1, j - 1, 0, prices, memo) + prices[i]);
		}
		// 空仓: 买回平空仓(付出prices[i]) 或 保持空仓 或 卖出平多仓(收入prices[i])
		return memo[i][j][k] = Math.max(dfs(i - 1, j, 2, prices, memo) - prices[i],
				Math.max(dfs(i - 1, j, 0, prices, memo), dfs(i - 1, j, 1, prices, memo) + prices[i]));
	}
}
