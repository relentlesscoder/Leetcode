package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 10/25/2019.
 * #0714
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {

	// time O(n), space O(1)
	public int maxProfitDP(int[] prices, int fee) {
		// 核心思路: 状态机DP, 买入需付费
		// cash: 第i天未持有的最大利润, hold: 持有的最大利润
		int n = prices.length, cash = 0, hold = Integer.MIN_VALUE / 2;
		for (int i = 0; i < n; i++) {
			int temp = cash;
			// 未持有: 保持不动 或 卖出
			cash = Math.max(cash, hold + prices[i]);
			// 持有: 保持不动 或 买入(从前天未持有状态转移, 跳过冷却期)
			hold = Math.max(hold, temp - prices[i] - fee);
		}
		return cash;
	}

	// time O(n), space O(n)
	public int maxProfitDPWithGrid(int[] prices, int fee) {
		// 核心思路: 与滚动变量版相同, 但保留完整二维数组
		// dp[i][0]: 第i天未持有的最大利润, dp[i][1]: 第i天持有的最大利润
		int n = prices.length;
		int[][] dp = new int[n + 2][2];
		dp[0][1] = Integer.MIN_VALUE / 2; // 第0天持有不合法
		for (int i = 0; i < n; i++) {
			// 未持有: 保持不动 或 卖出
			dp[i + 1][0] = Math.max(dp[i][0], dp[i][1] + prices[i]);
			// 持有: 保持不动 或 买入(付费)
			dp[i + 1][1] = Math.max(dp[i][1], dp[i][0] - prices[i] - fee);
		}
		return dp[n][0];
	}

	// time O(n), space O(n)
	public int maxProfitDFSWithMemorization(int[] prices, int fee) {
		// 核心思路: 记忆化DFS, 从最后一天往前递推, 买入时跳过冷却期(i-2)
		int n = prices.length;
		// memo[i][j]: 第i天状态j的最大利润
		int[][] memo = new int[n][2];
		for (int[] row : memo) {
			Arrays.fill(row, Integer.MIN_VALUE);
		}
		// 最终未持有才是合法终态
		return dfs(n - 1, 0, fee, prices, memo);
	}

	private int dfs(int i, int j, int fee, int[] prices, int[][] memo) {
		if (i < 0) {
			// 第0天之前: 未持有利润0, 持有不合法
			return j == 0 ? 0 : Integer.MIN_VALUE / 2;
		}
		if (memo[i][j] != Integer.MIN_VALUE) {
			return memo[i][j];
		}
		if (j == 0) {
			// 未持有: 保持不动 或 卖出
			return memo[i][j] = Math.max(dfs(i - 1, 0, fee, prices, memo),
					dfs(i - 1, 1, fee, prices, memo) + prices[i]);
		}
		// 持有: 保持不动 或 买入(从i-2天未持有转移, 跳过冷却期)
		return memo[i][j] = Math.max(dfs(i - 1, 1, fee, prices, memo),
				dfs(i - 1, 0, fee, prices, memo) - prices[i] - fee);
	}
}
