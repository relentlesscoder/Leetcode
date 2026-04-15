package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 10/25/2019.
 * #0309
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 */
public class BestTimeToBuyAndSellStockWithCooldown {

	// time O(n), space O(1)
	public int maxProfitDP(int[] prices) {
		// 核心思路: 状态机DP, 卖出后有1天冷却期, 买入需从i-2天的未持有状态转移
		// cash1: 第i天未持有的最大利润, cash0: 第i-1天未持有(用于冷却期), hold: 持有的最大利润
		int n = prices.length, cash0 = 0, cash1 = 0, hold = Integer.MIN_VALUE / 2;
		for (int i = 0; i < n; i++) {
			// 未持有: 保持不动 或 卖出
			int c = Math.max(cash1, hold + prices[i]);
			// 持有: 保持不动 或 买入(从前天未持有状态转移, 跳过冷却期)
			int h = Math.max(hold, (i >= 1 ? cash0 : 0) - prices[i]);
			// 滚动: cash0存前天, cash1存昨天
			cash0 = cash1;
			cash1 = c;
			hold = h;
		}
		return cash1;
	}

	// time O(n), space O(n)
	public int maxProfitDPWithGrid(int[] prices) {
		// 核心思路: 与滚动变量版相同, 但保留完整二维数组
		// dp[i][0]: 第i天未持有的最大利润, dp[i][1]: 第i天持有的最大利润
		int n = prices.length;
		int[][] dp = new int[n + 2][2];
		dp[0][1] = Integer.MIN_VALUE / 2; // 第0天持有不合法
		for (int i = 0; i < n; i++) {
			// 未持有: 保持不动 或 卖出
			dp[i + 1][0] = Math.max(dp[i][0], dp[i][1] + prices[i]);
			// 持有: 保持不动 或 买入(从前天dp[i-1][0]转移, 冷却期跳过昨天)
			dp[i + 1][1] = Math.max(dp[i][1], (i >= 1 ? dp[i - 1][0] : 0) - prices[i]);
		}
		return dp[n][0];
	}

	// time O(n), space O(n)
	public int maxProfitDFSWithMemorization(int[] prices) {
		// 核心思路: 记忆化DFS, 从最后一天往前递推, 买入时跳过冷却期(i-2)
		int n = prices.length;
		// memo[i][j]: 第i天状态j的最大利润
		int[][] memo = new int[n][2];
		for (int[] row : memo) {
			Arrays.fill(row, Integer.MIN_VALUE);
		}
		// 最终未持有才是合法终态
		return dfs(n - 1, 0, prices, memo);
	}

	// i: 当前天数, j: 0=未持有, 1=持有
	private int dfs(int i, int j, int[] prices, int[][] memo) {
		if (i < 0) {
			// 第0天之前: 未持有利润0, 持有不合法
			return j == 0 ? 0 : Integer.MIN_VALUE / 2;
		}
		if (memo[i][j] != Integer.MIN_VALUE) {
			return memo[i][j];
		}
		if (j == 0) {
			// 未持有: 保持不动 或 卖出
			return memo[i][j] = Math.max(dfs(i - 1, 0, prices, memo), dfs(i - 1, 1, prices, memo) + prices[i]);
		}
		// 持有: 保持不动 或 买入(从i-2天未持有转移, 跳过冷却期)
		return memo[i][j] = Math.max(dfs(i - 1, 1, prices, memo), dfs(i - 2, 0, prices, memo) - prices[i]);
	}
}
