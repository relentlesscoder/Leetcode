package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 08/08/2019.
 * #0583 https://leetcode.com/problems/delete-operation-for-two-strings/
 */
public class DeleteOperationForTwoStrings {

	// time O(m * n), space O(n)
	public int minDistance(String word1, String word2) {
		// 空间优化版 DP
		int m = word1.length(), n = word2.length();
		char[] s1 = word1.toCharArray(), s2 = word2.toCharArray();
		int[] dp = new int[n + 1];
		Arrays.setAll(dp, i -> i); // dp[0][j] = j，表示 word1 为空时需要删除 word2 的所有字符
		for (int i = 0; i < m; i++) {
			int x = dp[0]; // 记录 dp[j] 的上一个状态，即 dp[i - 1][j - 1]
			dp[0] = i + 1; // dp[i][0] = i，表示 word2 为空时需要删除 word1 的所有字符
			for (int j = 0; j < n; j++) {
				int val = dp[j + 1]; // 记录 dp[j + 1] 的值，更新 dp[j + 1] 之前需要用到它的值
				if (s1[i] == s2[j]) { // 两个字符匹配则不需要删除，状态转移方程 dp[i][j] = dp[i - 1][j - 1]
					dp[j + 1] = x;
				} else { // 不匹配则选择删除其中的一个，看看哪个能得到更少的操作
					// 状态转移方程 dp[i][j] = 1 + min(dp[i - 1][j], dp[i][j - 1])
					dp[j + 1] = 1 + Math.min(dp[j], dp[j + 1]);
				}
				x = val; // 更新 x 的值为下计算一个格子的 dp[i - 1][j - 1]
			}
		}
		return dp[n];
	}

	// time O(m * n), space O(m * n)
	public int minDistanceFindLCS(String word1, String word2) {
		// 反向思考 - 通过求最长公共子序列来计算最少删除次数
		return word1.length() + word2.length() -
				2 * longestCommonSubsequence(word1, word2);
	}

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

	// time O(m * n), space O(m * n)
	public int minDistanceDPWithGrid(String word1, String word2) {
		// 把记忆化搜索翻译成 DP
		int m = word1.length(), n = word2.length();
		char[] s1 = word1.toCharArray(), s2 = word2.toCharArray();
		int[][] dp = new int[m + 1][n + 1];
		Arrays.setAll(dp[0], i -> i);
		for (int i = 0; i < m; i++) {
			dp[i + 1][0] = i + 1;
			for (int j = 0; j < n; j++) {
				if (s1[i] == s2[j]) {
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					dp[i + 1][j + 1] = 1 + Math.min(dp[i + 1][j], dp[i][j + 1]);
				}
			}
		}
		return dp[m][n];
	}

	// time O(m * n), space O(m * n)
	public int minDistanceDFSWithMemorization(String word1, String word2) {
		// 记忆化搜索
		int m = word1.length(), n = word2.length();
		int[][] memo = new int[m][n];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return dfs(m - 1, n - 1, word1.toCharArray(), word2.toCharArray(), memo);
	}

	private int dfs(int i, int j, char[] s1, char[] s2, int[][] memo) {
		if (i == -1 && j == -1) { // 两个字符串都被完全匹配了
			return 0;
		}
		if (i == -1) { // word1 已经被完全匹配了，剩下的 word2 的字符都要被删除
			return j + 1;
		}
		if (j == -1) { // word2 已经被完全匹配了，剩下的 word1 的字符都要被删除
			return i + 1;
		}
		if (memo[i][j] != -1) {
			return memo[i][j];
		}
		if (s1[i] == s2[j]) { // 两个字符匹配则不需要删除
			return memo[i][j] = dfs(i - 1, j - 1, s1, s2, memo);
		}
		// 不匹配则选择删除其中的一个，看看哪个能得到更少的操作
		return memo[i][j] = 1 + Math.min(dfs(i - 1, j, s1, s2, memo),
				dfs(i, j - 1, s1, s2, memo));
	}
}
