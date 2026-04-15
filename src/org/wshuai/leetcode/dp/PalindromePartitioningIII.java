package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 12/04/2019.
 * #1278 https://leetcode.com/problems/palindrome-partitioning-iii/
 */
public class PalindromePartitioningIII {

	private static final int MAX = 1_000;

	// time O(n^2 * k), space O(n^2)
	public int palindromePartitionDPWithArray(String s, int k) {
		// 核心思路: 划分型DP, 将字符串划分成k段, 最小化每段变成回文的总修改次数
		// 预计算cost[i][j] = s[i..j]变成回文所需的最少修改次数
		// 滚动数组优化: dp[j] = 前j个字符划分成当前轮(i+1段)的最小修改次数
		char[] sc = s.toCharArray();
		int n = sc.length;
		int[][] cost = getCost(sc);
		// dp[j]: 前j个字符在当前轮下的最优解
		int[] dp = new int[n + 1];
		Arrays.fill(dp, MAX);
		dp[0] = 0; // 0个字符, 0段, 修改次数为0
		// 每轮增加一段
		for (int i = 0; i < k; i++) {
			// 从右往左更新, 防止覆盖上一轮dp[x]
			for (int j = n - 1; j >= i; j--) {
				int res = MAX;
				// 枚举最后一段的起点x, 最后一段为s[x..j]
				for (int x = j; x >= i; x--) {
					res = Math.min(res, cost[x][j] + dp[x]);
				}
				dp[j + 1] = res;
			}
		}
		return dp[n];
	}

	// time O(n^2 * k), space O(n^2 + n * k)
	public int palindromePartitionDPWithGrid(String s, int k) {
		// 核心思路: 与滚动数组版相同, 但保留完整二维数组
		// dp[i][j]: 前j个字符划分成i段的最小修改次数
		char[] sc = s.toCharArray();
		int n = sc.length;
		int[][] cost = getCost(sc), dp = new int[k + 1][n + 1];
		Arrays.fill(dp[0], MAX);
		dp[0][0] = 0; // 0个字符, 0段, 修改次数为0
		// 每轮增加一段
		for (int i = 0; i < k; i++) {
			for (int j = n - 1; j >= i; j--) {
				int res = MAX;
				// 枚举最后一段的起点x, 最后一段为s[x..j]
				for (int x = j; x >= i; x--) {
					// cost[x][j] = s[x..j]变回文的代价, dp[i][x] = 前x个字符划分i段的最优解
					res = Math.min(res, cost[x][j] + dp[i][x]);
				}
				dp[i + 1][j + 1] = res;
			}
		}
		return dp[k][n];
	}

	// time O(n^2 * k), space O(n^2 + n * k)
	public int palindromePartitionDFSWithMemorization(String s, int k) {
		// 核心思路: 记忆化DFS, 从右往左划分, 每次枚举最后一段的起点
		char[] sc = s.toCharArray();
		int n = sc.length;
		int[][] cost = getCost(sc), memo = new int[k][n];
		// memo[i][j]: 前j+1个字符划分成i+1段的最小修改次数
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		// 初始: k-1表示还需划分k段, j=n-1为最右字符
		return dfs(k - 1, n - 1, cost, memo);
	}

	// i: 剩余需要划分的段数-1, j: 当前处理到的最右字符索引
	private int dfs(int i, int j, int[][] cost, int[][] memo) {
		if (i == -1) {
			// 所有段已划分完, j==-1表示字符恰好用完
			return j == -1 ? 0 : MAX;
		}
		if (j == -1) {
			// 字符用完但还有段未划分, 不合法
			return MAX;
		}
		if (memo[i][j] != -1) {
			return memo[i][j];
		}
		int res = MAX;
		// 枚举当前段的起点k, 当前段为s[k..j]
		for (int k = j; k >= i; k--) {
			// cost[k][j] = s[k..j]变回文的代价, 递归处理剩余部分
			res = Math.min(res, cost[k][j] + dfs(i - 1, k - 1, cost, memo));
		}
		return memo[i][j] = res;
	}

	// 预计算cost[i][j]: s[i..j]变成回文所需的最少字符修改次数
	// 区间DP: 从短到长枚举, cost[i][j] = cost[i+1][j-1] + (首尾不同则+1)
	private int[][] getCost(char[] s) {
		int n = s.length;
		int[][] cost = new int[n][n];
		for (int l = 2; l <= n; l++) {
			for (int i = 0; i < n - l + 1; i++) {
				int j = i + l - 1;
				// 首尾相同则不需要修改, 否则+1
				cost[i][j] = cost[i + 1][j - 1] + (s[i] == s[j] ? 0 : 1);
			}
		}
		return cost;
	}
}
