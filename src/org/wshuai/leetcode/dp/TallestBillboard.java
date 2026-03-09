package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 12/23/2019.
 * #0956 https://leetcode.com/problems/tallest-billboard/
 */
public class TallestBillboard {

	private static final int MIN = -(int) 1e5;

	// time O(n * s), space O(s)
	public int tallestBillboardDP(int[] rods) {
		// 空间优化版 DP
		int n = rods.length, sum = 0;
		for (int x : rods) {
			sum += x;
		}
		int m = 2 * sum + 1;
		int[] pre = new int[m];
		Arrays.fill(pre, MIN);
		pre[sum] = 0;
		for (int i = 0; i < n; i++) {
			int[] dp = new int[m];
			Arrays.fill(dp, MIN);
			for (int j = 0; j < m; j++) {
				if (pre[j] == MIN) {
					continue;
				}
				if (j >= rods[i]) {
					dp[j - rods[i]] = Math.max(dp[j - rods[i]], pre[j] + rods[i]);
				}
				dp[j] = Math.max(dp[j], pre[j]);
				if (j + rods[i] < m) {
					dp[j + rods[i]] = Math.max(dp[j + rods[i]], pre[j]);
				}
			}
			pre = dp;
		}
		return pre[sum];
	}

	// time O(n * s), space O(s)
	public int tallestBillboardDPWithGrid(int[] rods) {
		// 将记忆化搜索翻译成 DP
		int n = rods.length, sum = 0;
		for (int x : rods) {
			sum += x;
		}
		int m = 2 * sum + 1;
		int[][] dp = new int[n + 1][m];
		for (int[] row : dp) {
			Arrays.fill(row, MIN);
		}
		dp[0][sum] = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (dp[i][j] == MIN) {
					continue;
				}
				if (j >= rods[i]) {
					dp[i + 1][j - rods[i]] = Math.max(dp[i + 1][j - rods[i]], dp[i][j] + rods[i]);
				}
				dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);
				if (j + rods[i] < m) {
					dp[i + 1][j + rods[i]] = Math.max(dp[i + 1][j + rods[i]], dp[i][j]);
				}
			}
		}
		return dp[n][sum];
	}

	// time O(n * s), space O(n * s)
	public int tallestBillboardDFSWithMemorization(int[] rods) {
		// 记忆化搜索
		int n = rods.length, sum = 0;
		for (int x : rods) {
			sum += x;
		}
		// s 的范围是 [-sum, sum]，为了把 s 映射到 memo 数组的索引上，我们把 s 加上 sum。
		int m = 2 * sum + 1;
		int[][] memo = new int[n][m];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return dfs(n - 1, 0, sum, rods, memo);
	}

	private int dfs(int i, int s, int sum, int[] rods, int[][] memo) {
		if (i == -1) { // s == 0 代表两边平衡，返回 0；否则不平衡，返回一个很大的负数。
			return s == 0 ? 0 : MIN;
		}
		if (memo[i][s + sum] != -1) {
			return memo[i][s + sum];
		}
		return memo[i][s + sum] = Math.max(dfs(i - 1, s, sum, rods, memo), // 不选当前木棍
				Math.max(
						// 注意放在左边要加上当前木棍的长度到答案里因为我们是以左边的长度为基准来计算差值的
						dfs(i - 1, s + rods[i], sum, rods, memo) + rods[i], // 选当前木棍放在左边
						dfs(i - 1, s - rods[i], sum, rods, memo))); // 选当前木棍放在右边
	}
}
