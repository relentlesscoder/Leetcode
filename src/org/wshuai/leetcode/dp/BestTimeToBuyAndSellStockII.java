package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 10/25/2019.
 * #0122 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BestTimeToBuyAndSellStockII {

	// time O(n), space O(1)
	public int maxProfitDP(int[] prices) {
		// 核心思路: 状态机DP, 两个状态: 持有股票(hold)和未持有(cash), 不限交易次数
		// hold: 当前持有股票时的最大利润, cash: 当前未持有股票时的最大利润
		int n = prices.length, hold = Integer.MIN_VALUE / 2, cash = 0;
		for (int i = 0; i < n; i++) {
			int temp = cash; // 暂存上一步的cash, 防止被覆盖
			// 未持有: 保持不动 或 卖出(hold + 卖价)
			cash = Math.max(cash, hold + prices[i]);
			// 持有: 保持不动 或 买入(上一步cash - 买价)
			hold = Math.max(hold, temp - prices[i]);
		}
		return cash;
	}

	// time O(n), space O(n)
	public int maxProfitDPWithGrid(int[] prices) {
		// 核心思路: 与滚动变量版相同, 但保留完整二维数组
		// dp[i][0]: 第i天结束时未持有股票的最大利润, dp[i][1]: 持有股票的最大利润
		int n = prices.length;
		int[][] dp = new int[n + 1][2];
		dp[0][0] = 0; // 第0天未持有, 利润0
		dp[0][1] = Integer.MIN_VALUE / 2; // 第0天不可能持有, 设为负无穷
		for (int i = 0; i < n; i++) {
			// 未持有: 保持不动 或 卖出
			dp[i + 1][0] = Math.max(dp[i][0], dp[i][1] + prices[i]);
			// 持有: 保持不动 或 买入
			dp[i + 1][1] = Math.max(dp[i][1], dp[i][0] - prices[i]);
		}
		return dp[n][0];
	}

	// time O(n), space O(n)
	public int maxProfitDFSWithMemorization(int[] prices) {
		// 核心思路: 记忆化DFS, 从最后一天往前递推, j=0未持有/j=1持有
		int n = prices.length;
		// memo[i][j]: 第i天状态j时的最大利润
		int[][] memo = new int[n][2];
		for (int[] row : memo) {
			Arrays.fill(row, Integer.MIN_VALUE); // 初始化为负无穷, 表示未计算过
		}
		// 最终未持有股票才是合法终态
		return dfs(n - 1, 0, prices, memo);
	}

	// i: 当前天数, j: 0=未持有, 1=持有
	private int dfs(int i, int j, int[] nums, int[][] memo) {
		if (i == -1) {
			// 第0天之前: 未持有利润0, 持有不合法(负无穷)
			return j == 0 ? 0 : Integer.MIN_VALUE / 2;
		}
		if (memo[i][j] != Integer.MIN_VALUE) {
			return memo[i][j];
		}
		if (j == 0) {
			// 未持有: 保持不动 或 卖出(从持有状态 + 卖价)
			return memo[i][j] = Math.max(dfs(i - 1, 0, nums, memo), dfs(i - 1, 1, nums, memo) + nums[i]);
		}
		// 持有: 保持不动 或 买入(从未持有状态 - 买价)
		return memo[i][j] = Math.max(dfs(i - 1, 1, nums, memo), dfs(i - 1, 0, nums, memo) - nums[i]);
	}
}
