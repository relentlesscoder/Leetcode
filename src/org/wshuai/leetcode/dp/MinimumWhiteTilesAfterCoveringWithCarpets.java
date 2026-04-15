package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 04/06/2026.
 * #2209
 * https://leetcode.com/problems/minimum-white-tiles-after-covering-with-carpets/
 */
public class MinimumWhiteTilesAfterCoveringWithCarpets {

	// time O(n * numCarpets), space O(n)
	public int minimumWhiteTilesDPWithRollingArray(String floor, int numCarpets, int carpetLen) {
		// 核心思路: dp[f] = 前f个格子用c+1条地毯覆盖后的最少白色瓷砖数
		// 每个格子两种选择: 不铺地毯(累加白色) 或 铺一条地毯覆盖末尾carpetLen个格子
		// 滚动数组优化: pre存上一轮(少一条地毯)的结果, dp存当前轮
		int n = floor.length();
		char[] s = floor.toCharArray();
		// pre[f]: 前f个格子不用地毯时的白色瓷砖数(即前缀和)
		int[] pre = new int[n + 1];
		for (int f = 0; f < n; f++) {
			pre[f + 1] = pre[f] + (s[f] - '0');
		}
		// 每轮增加一条地毯
		for (int c = 0; c < numCarpets; c++) {
			int[] dp = new int[n + 1];
			for (int f = 0; f < n; f++) {
				if (f + 1 <= carpetLen * (c + 1)) {
					// c+1条地毯足以覆盖前f+1个格子, 白色数为0
					dp[f + 1] = 0;
					continue;
				}
				// 不铺: 保留格子f的颜色, 继承dp[f]
				// 铺: 地毯覆盖[f+1-carpetLen, f], 剩余部分用上一轮pre的结果
				dp[f + 1] = Math.min(dp[f] + (s[f] - '0'),
						pre[Math.max(f + 1 - carpetLen, 0)]);
			}
			// 滚动: 当前轮结果作为下一轮的pre
			pre = dp;
		}
		return pre[n];
	}

	// time O(n * numCarpets), space O(n * numCarpets)
	public int minimumWhiteTilesDPWithGrid(String floor, int numCarpets, int carpetLen) {
		// 核心思路: 与滚动数组版相同, 但保留完整二维数组
		// dp[c][f]: 前f个格子用c条地毯覆盖后的最少白色瓷砖数
		int n = floor.length();
		char[] s = floor.toCharArray();
		int[][] dp = new int[numCarpets + 1][n + 1];
		// base case: 0条地毯, dp[0][f] = 前f个格子的白色瓷砖前缀和
		for (int f = 0; f < n; f++) {
			dp[0][f + 1] = dp[0][f] + (s[f] - '0');
		}
		// 每轮增加一条地毯
		for (int c = 0; c < numCarpets; c++) {
			for (int f = 0; f < n; f++) {
				if (f + 1 <= carpetLen * (c + 1)) {
					// c+1条地毯足以覆盖前f+1个格子
					dp[c + 1][f + 1] = 0;
					continue;
				}
				// 不铺: 保留格子f的颜色, 继承dp[c+1][f]
				// 铺: 地毯覆盖[f+1-carpetLen, f], 剩余部分用少一条地毯的dp[c]
				dp[c + 1][f + 1] = Math.min(dp[c + 1][f] + (s[f] - '0'),
						dp[c][Math.max(f + 1 - carpetLen, 0)]);
			}
		}
		return dp[numCarpets][n];
	}

	// time O(n * numCarpets), space O(n * numCarpets)
	public int minimumWhiteTilesDFSWithMemorization(String floor, int numCarpets, int carpetLen) {
		// 核心思路: 记忆化DFS, 从右往左处理每个格子, 选择不铺或铺地毯
		int n = floor.length();
		// memo[f][c]: 前f+1个格子用c+1条地毯的最少白色瓷砖数
		int[][] memo = new int[n][numCarpets];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return dfs(n - 1, numCarpets - 1, carpetLen, floor.toCharArray(), memo);
	}

	// f: 当前处理的格子索引(从右往左), c: 剩余可用地毯数-1, l: 地毯长度
	private int dfs(int f, int c, int l, char[] s, int[][] memo) {
		if (f + 1 <= l * (c + 1)) {
			// 剩余地毯足以覆盖所有前f+1个格子, 白色数为0
			return 0;
		}
		if (c == -1) {
			// 没有地毯可用, 只能累加剩余格子的白色数
			return dfs(f - 1, c, l, s, memo) + (s[f] - '0');
		}
		if (memo[f][c] != -1) {
			return memo[f][c];
		}
		// 不铺: 保留格子f的颜色, 向左继续
		int res = dfs(f - 1, c, l, s, memo) + (s[f] - '0');
		// 铺: 地毯覆盖[f-l+1, f], 消耗一条地毯, 跳到f-l继续
		res = Math.min(res, dfs(f - l, c - 1, l, s, memo));
		return memo[f][c] = res;
	}
}
