package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 09/19/2019.
 * #1143 https://leetcode.com/problems/longest-common-subsequence/
 */
public class LongestCommonSubsequence {

	// time O(m * n), space O(n)
	public int longestCommonSubsequence(String text1, String text2) {
		// 继续优化
		int m = text1.length(), n = text2.length();
		int[] dp = new int[n + 1];
		for (int i = 0; i < m; i++) {
			int pre = dp[0]; // 记录 dp[j] 的上一个状态，即 dp[i - 1][j - 1]
			for (int j = 0; j < n; j++) {
				int x = dp[j + 1];
				if (text1.charAt(i) == text2.charAt(j)) {
					dp[j + 1] = 1 + pre;
				} else {
					dp[j + 1] = Math.max(dp[j], dp[j + 1]);
				}
				pre = x; // 更新 pre 的值为下计算一个格子的 dp[i - 1][j - 1]
			}
		}
		return dp[n];
	}

	// time O(m * n), space O(n)
	public int longestCommonSubsequenceDPWithRollingArray(String text1, String text2) {
		// 空间优化版 DP
		int m = text1.length(), n = text2.length();
		int[] dp = new int[n + 1];
		for (int i = 0; i < m; i++) {
			int[] next = new int[n + 1];
			for (int j = 0; j < n; j++) {
				if (text1.charAt(i) == text2.charAt(j)) {
					next[j + 1] = 1 + dp[j];
				} else {
					next[j + 1] = Math.max(next[j], dp[j + 1]);
				}
			}
			dp = next;
		}
		return dp[n];
	}

	// time O(m * n), space O(m * n)
	public int longestCommonSubsequenceDPWithGrid(String text1, String text2) {
		// 把记忆化搜索翻译成 DP
		int m = text1.length(), n = text2.length();
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (text1.charAt(i) == text2.charAt(j)) {
					dp[i + 1][j + 1] = 1 + dp[i][j];
				} else {
					dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
				}
			}
		}
		return dp[m][n];
	}

	// time O(m * n), space O(m * n)
	public int longestCommonSubsequenceDFSWithMemorization(String text1, String text2) {
		// 记忆化搜索
		int m = text1.length(), n = text2.length();
		int[][] memo = new int[m][n];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return dfs(m - 1, n - 1, text1.toCharArray(), text2.toCharArray(), memo);
	}

	private int dfs(int i, int j, char[] s1, char[] s2, int[][] memo) {
		if (i == -1 || j == -1) { // 一个字符串已经被完全匹配了
			return 0;
		}
		if (memo[i][j] != -1) {
			return memo[i][j];
		}
		if (s1[i] == s2[j]) { // 两个字符匹配则同时选择 text1[i] 和 text2[j]
			return memo[i][j] = 1 + dfs(i - 1, j - 1, s1, s2, memo);
		}
		// 选择 text1[i] 或 text2[j] 中的一个，看看哪个能得到更长的公共子序列
		return memo[i][j] = Math.max(dfs(i - 1, j, s1, s2, memo),
				dfs(i, j - 1, s1, s2, memo));
	}
}
