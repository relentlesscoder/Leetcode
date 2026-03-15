package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 09/30/2019.
 * #0712 https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
 */
public class MinimumASCIIDeleteSumForTwoStrings {

	// time O(m * n), space O(n)
	public int minimumDeleteSum(String s1, String s2) {
		// 空间优化版 DP
		int m = s1.length(), n = s2.length();
		int[] dp = new int[n + 1];
		for (int i = 0; i < n; i++) {
			dp[i + 1] = dp[i] + s2.charAt(i);
		}
		for (int i = 0; i < m; i++) {
			int pre = dp[0];
			dp[0] += s1.charAt(i);
			for (int j = 0; j < n; j++) {
				int x = dp[j + 1];
				if (s1.charAt(i) == s2.charAt(j)) {
					dp[j + 1] = pre;
				} else {
					dp[j + 1] = Math.min(s1.charAt(i) + dp[j + 1], s2.charAt(j) + dp[j]);
				}
				pre = x;
			}
		}
		return dp[n];
	}

	// time O(m * n), space O(m * n)
	public int minimumDeleteSumDPWithGrid(String s1, String s2) {
		// 把记忆化搜索翻译成 DP
		int m = s1.length(), n = s2.length();
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i < n; i++) {
			dp[0][i + 1] = dp[0][i] + s2.charAt(i);
		}
		for (int i = 0; i < m; i++) {
			dp[i + 1][0] = dp[i][0] + s1.charAt(i);
			for (int j = 0; j < n; j++) {
				if (s1.charAt(i) == s2.charAt(j)) {
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					dp[i + 1][j + 1] = Math.min(s1.charAt(i) + dp[i][j + 1], s2.charAt(j) + dp[i + 1][j]);
				}
			}
		}
		return dp[m][n];
	}

	// time O(m * n), space O(m * n)
	public int minimumDeleteSumDFSWithMemoization(String s1, String s2) {
		// 记忆化搜索
		int m = s1.length(), n = s2.length();
		char[] a1 = s1.toCharArray(), a2 = s2.toCharArray();
		// 预处理前缀和，计算删除剩余字符串的 ASCII 码总和
		int[] pre1 = new int[m + 1], pre2 = new int[n + 1];
		for (int i = 0; i < m; i++) {
			pre1[i + 1] = pre1[i] + s1.charAt(i);
		}
		for (int i = 0; i < n; i++) {
			pre2[i + 1] = pre2[i] + s2.charAt(i);
		}
		int[][] memo = new int[m + 1][n + 1];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return dfs(m - 1, n - 1, pre1, pre2, a1, a2, memo);
	}

	private int dfs(int i, int j, int[] p1, int[] p2, char[] s1, char[] s2, int[][] memo) {
		if (i == -1 && j == -1) { // 两个字符串都为空，不需要删除任何字符
			return 0;
		}
		if (i == -1 || j == -1) { // 其中一个字符串为空，需要删除另一个字符串的所有字符
			return i == -1 ? p2[j + 1] : p1[i + 1];
		}
		if (memo[i][j] != -1) {
			return memo[i][j];
		}
		if (s1[i] == s2[j]) { // 两个字符匹配则不需要删除，状态转移方程 dfs(i, j) = dfs(i - 1, j - 1)
			return memo[i][j] = dfs(i - 1, j - 1, p1, p2, s1, s2, memo);
		} else { // 不匹配则选择删除其中的一个，看看哪个能得到更少的 ASCII 码总和
			return memo[i][j] = Math.min(s1[i] + dfs(i - 1, j, p1, p2, s1, s2, memo),
					s2[j] + dfs(i, j - 1, p1, p2, s1, s2, memo));
		}
	}
}
