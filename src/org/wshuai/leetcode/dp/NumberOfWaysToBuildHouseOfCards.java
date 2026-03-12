package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 03/08/2026.
 * #2189 https://leetcode.com/problems/number-of-ways-to-build-house-of-cards/
 */
public class NumberOfWaysToBuildHouseOfCards {

	// time O(n * m), space O(n * m)
	public int houseOfCardsDFSWithMemorization(int n) {
		// 记忆化搜索
		int m = (n + 1) / 3; // 最多搭 m 组三角牌，m 组需要 3 * m - 1 张牌
		int[][] memo = new int[n + 1][m + 1];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return dfs(n, (n + 1) / 3, memo);
	}

	private int dfs(int c, int t, int[][] memo) {
		if (c == 0) {
			return 1;
		}
		if (memo[c][t] != -1) {
			return memo[c][t];
		}
		int res = 0;
		// 计算剩余 c 张牌，最多还能在当前层搭 1 到 t 组三角牌的方案数
		for (int i = 1; i <= t; i++) {
			int cost = 3 * i - 1; // 搭 i 组三角牌需要 3 * i - 1 张牌
			if (cost > c) { // 剩余牌不够搭 i 组三角牌了，后面更大的 i 就更不够了，直接退出循环
				break;
			}
			// 搭 i 组三角牌后，剩余 c - cost 张牌，下一层最多还能搭 i - 1 组三角牌
			res += dfs(c - cost, i - 1, memo);
		}
		return memo[c][t] = res;
	}
}
