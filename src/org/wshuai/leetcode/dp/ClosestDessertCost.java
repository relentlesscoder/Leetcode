package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 10/03/2023.
 * #1774 https://leetcode.com/problems/closest-dessert-cost/
 */
public class ClosestDessertCost {

	// time O(m * t), space O(t)
	public int closestCostDP(int[] baseCosts, int[] toppingCosts, int target) {
		// 空间优化版 DP
		int[] unique = Arrays.stream(baseCosts).distinct().toArray();
		int res = (int) 1e5, m = toppingCosts.length;
		int[] dp = new int[target + 1];
		Arrays.setAll(dp, i -> i);
		for (int i = 0; i < m; i++) {
			int x = toppingCosts[i], y = 2 * toppingCosts[i];
			for (int c = target; c >= 0; c--) {
				if (c <= x) {
					dp[c] = compare(dp[c], c - x);
				} else if (c <= y) {
					dp[c] = compare(dp[c], compare(dp[c - x], c - y));
				} else {
					dp[c] = compare(dp[c], compare(dp[c - x], dp[c - y]));
				}
			}
		}
		for (int x : unique) {
			res = compare(res, target <= x ? target - x : dp[target - x]);
		}
		return target - res;
	}

	// time O(m * t), space O(m * t)
	public int closestCostDPWithGrid(int[] baseCosts, int[] toppingCosts, int target) {
		// 将记忆化搜素翻译成 DP
		int[] unique = Arrays.stream(baseCosts).distinct().toArray();
		int res = (int) 1e5, m = toppingCosts.length;
		int[][] dp = new int[m + 1][target + 1];
		Arrays.setAll(dp[0], i -> i);
		for (int i = 0; i < m; i++) {
			int x = toppingCosts[i], y = 2 * toppingCosts[i];
			for (int c = target; c >= 0; c--) {
				if (c <= x) { // 不选或者选一个 - 选一个变成负数直接返回
					dp[i + 1][c] = compare(dp[i][c], c - x);
				} else if (c <= y) { // 不选，选一个或者选两个 - 选两个变成负数直接返回
					dp[i + 1][c] = compare(dp[i][c], compare(dp[i][c - x], c - y));
				} else { // 不选，选一个或者选两个
					dp[i + 1][c] = compare(dp[i][c], compare(dp[i][c - x], dp[i][c - y]));
				}
			}
		}
		for (int x : unique) {
			res = compare(res, target <= x ? target - x : dp[m][target - x]);
		}
		return target - res;
	}

	// time O(m * t), space O(m * t)
	public int closestCostDFSWithMemorization(int[] baseCosts, int[] toppingCosts, int target) {
		// 记忆化搜索
		int[] unique = Arrays.stream(baseCosts).distinct().toArray();
		int res = (int) 1e5, m = toppingCosts.length;
		int[][] memo = new int[m][target + 1];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		for (int x : unique) {
			res = compare(res, dfs(m - 1, target - x, toppingCosts, memo));
		}
		return target - res;
	}

	private int dfs(int i, int target, int[] costs, int[][] memo) {
		if (i == -1 || target <= 0) {
			return target;
		}
		if (memo[i][target] != -1) {
			return memo[i][target];
		}
		return memo[i][target] = compare(dfs(i - 1, target, costs, memo),
				compare(dfs(i - 1, target - costs[i], costs, memo),
						dfs(i - 1, target - 2 * costs[i], costs, memo)));
	}

	// time O(n * m^3), space O(n + m)
	public int closestCostDFS(int[] baseCosts, int[] toppingCosts, int target) {
		// 深度搜索
		int[] unique = Arrays.stream(baseCosts).distinct().toArray();
		int res = (int) 1e5, m = toppingCosts.length;
		for (int x : unique) {
			res = compare(res, dfs1(m - 1, target - x, toppingCosts));
		}
		return target - res;
	}

	private int dfs1(int i, int target, int[] costs) {
		if (i == -1 || target <= 0) {
			return target;
		}
		return compare(dfs1(i - 1, target, costs),
				compare(dfs1(i - 1, target - costs[i], costs),
						dfs1(i - 1, target - 2 * costs[i], costs)));
	}

	private static int compare(int a, int b) {
		int x = a >= 0 ? a : -a;
		int y = b >= 0 ? b : -b;
		if (x == y) {
			return a <= b ? b : a;
		} else if (x < y) {
			return a;
		} else {
			return b;
		}
	}
}
