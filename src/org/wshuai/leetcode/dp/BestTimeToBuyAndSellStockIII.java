package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 10/25/2019.
 * #0123 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class BestTimeToBuyAndSellStockIII {

	// time O(n), space O(1)
	public int maxProfitDP(int[] prices) {
		// 核心思路: 状态机DP, 三维状态(天数, 已完成交易数, 是否持有)压缩到二维
		// dp[j][0]: 已完成j次交易且未持有的最大利润, dp[j][1]: 已完成j次交易且持有的最大利润
		// 关键点: j从小到大遍历时, dp[j][0]在本轮已被更新(今天的值), dp[j+1][1]读到的是今天的dp[j][0]
		// 而三维版本读的是昨天的值. 但结果相同, 因为:
		// dp[j+1][1] = dp[j][0] - prices[i]
		// = (dp[j][1] + prices[i]) - prices[i] (若dp[j][0]因今天卖出而更新)
		// = dp[j][1]
		// +prices[i]和-prices[i]抵消, 等价于同一天买卖利润为0, 和没有这次交易效果一样
		int n = prices.length, k = 2;
		int[][] dp = new int[k + 1][2];
		for (int j = 0; j <= k; j++) {
			dp[j] = new int[] { 0, Integer.MIN_VALUE / 2 }; // 持有状态初始化为负无穷
		}
		for (int i = 0; i < n; i++) {
			// 0次交易: 没买过也没卖过, 利润始终为0
			dp[0][0] = dp[0][1] = 0;
			for (int j = 0; j < k; j++) {
				// 未持有: 保持不动 或 卖出(完成第j+1次交易)
				dp[j + 1][0] = Math.max(dp[j + 1][0], dp[j + 1][1] + prices[i]);
				// 持有: 保持不动 或 买入(从j次交易未持有状态转移)
				dp[j + 1][1] = Math.max(dp[j + 1][1], dp[j][0] - prices[i]);
			}
		}
		return dp[k][0];
	}

	// time O(n), space O(n)
	public int maxProfitDPWithGrid(int[] prices) {
		// 核心思路: 与压缩版相同, 但保留完整三维数组
		// dp[i][j][0]: 前i天完成j次交易且未持有, dp[i][j][1]: 前i天完成j次交易且持有
		int n = prices.length, k = 2;
		int[][][] dp = new int[n + 1][k + 1][2];
		for (int j = 0; j <= k; j++) {
			dp[0][j] = new int[] { 0, Integer.MIN_VALUE / 2 }; // 第0天持有不合法
		}
		for (int i = 0; i < n; i++) {
			dp[i + 1][0][0] = dp[i + 1][0][1] = 0; // 0次交易利润为0
			for (int j = 0; j < k; j++) {
				// 未持有: 保持不动 或 卖出
				dp[i + 1][j + 1][0] = Math.max(dp[i][j + 1][0], dp[i][j + 1][1] + prices[i]);
				// 持有: 保持不动 或 买入(从j次交易未持有转移, 买入开启第j+1次交易)
				dp[i + 1][j + 1][1] = Math.max(dp[i][j + 1][1], dp[i][j][0] - prices[i]);
			}
		}
		return dp[n][k][0];
	}

	// time O(n), space O(n)
	public int maxProfitDFSWithMemorization(int[] prices) {
		// 核心思路: 记忆化DFS, 三维状态(天数i, 剩余交易数j, 是否持有h)
		int n = prices.length;
		// memo[i][j][h]: 第i天剩余j+1次交易机会, h=0未持有/h=1持有
		int[][][] memo = new int[n][2][2];
		for (int[][] matrix : memo) {
			for (int[] row : matrix) {
				Arrays.fill(row, Integer.MIN_VALUE);
			}
		}
		return dfs(n - 1, 1, 0, prices, memo);
	}

	// i: 当前天数, j: 剩余交易次数-1, h: 0=未持有, 1=持有
	private int dfs(int i, int j, int h, int[] nums, int[][][] memo) {
		if (i == -1) {
			// 第0天之前: 未持有利润0, 持有不合法(负无穷)
			return h == 0 ? 0 : Integer.MIN_VALUE / 2;
		}
		if (j == -1) {
			// 交易次数用完, 无法再交易, 利润为0
			return 0;
		}
		if (memo[i][j][h] != Integer.MIN_VALUE) {
			return memo[i][j][h];
		}
		if (h == 0) {
			// 未持有: 保持不动 或 卖出(完成一次交易)
			return memo[i][j][h] = Math.max(dfs(i - 1, j, 0, nums, memo), dfs(i - 1, j, 1, nums, memo) + nums[i]);
		}
		// 持有: 保持不动 或 买入(消耗一次交易机会, j-1)
		return memo[i][j][h] = Math.max(dfs(i - 1, j, 1, nums, memo), dfs(i - 1, j - 1, 0, nums, memo) - nums[i]);
	}
}
