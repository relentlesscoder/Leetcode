package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 02/05/2017.
 * #0097 https://leetcode.com/problems/interleaving-string/
 */
public class InterleavingString {

	// time O(m * n), space O(n)
	public boolean isInterleave(String s1, String s2, String s3) {
		// 空间优化版 DP
		int m = s1.length(), n = s2.length(), k = s3.length();
		if (m + n != k) {
			return false;
		}
		boolean[] dp = new boolean[n + 1];
		dp[0] = true;
		for (int j = 0; j < n; j++) {
			dp[j + 1] = s2.charAt(j) == s3.charAt(j) && dp[j];
		}
		for (int i = 0; i < m; i++) {
			dp[0] = s1.charAt(i) == s3.charAt(i) && dp[0];
			for (int j = 0; j < n; j++) {
				dp[j + 1] = (s2.charAt(j) == s3.charAt(i + j + 1) && dp[j])
						|| (s1.charAt(i) == s3.charAt(i + j + 1) && dp[j + 1]);
			}
		}
		return dp[n];
	}

	// time O(m * n), space O(m * n)
	public boolean isInterleaveDPWithGrid(String s1, String s2, String s3) {
		// 把记忆化搜索翻译成 DP
		int m = s1.length(), n = s2.length(), k = s3.length();
		if (m + n != k) {
			return false;
		}
		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[0][0] = true;
		for (int j = 0; j < n; j++) {
			dp[0][j + 1] = s2.charAt(j) == s3.charAt(j) && dp[0][j];
		}
		for (int i = 0; i < m; i++) {
			dp[i + 1][0] = s1.charAt(i) == s3.charAt(i) && dp[i][0];
			for (int j = 0; j < n; j++) {
				dp[i + 1][j + 1] = (s2.charAt(j) == s3.charAt(i + j + 1) && dp[i + 1][j])
						|| (s1.charAt(i) == s3.charAt(i + j + 1) && dp[i][j + 1]);
			}
		}
		return dp[m][n];
	}

	// time O(m * n), space O(m * n)
	public boolean isInterleaveDFSWithMemorization(String s1, String s2, String s3) {
		// 记忆化搜索
		int m = s1.length(), n = s2.length(), k = s3.length();
		if (m + n != k) {
			return false;
		}
		Boolean[][] memo = new Boolean[m][n];
		return dfs(m - 1, n - 1, s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), memo);
	}

	private boolean dfs(int i, int j, char[] s1, char[] s2, char[] s3, Boolean[][] memo) {
		// 如果 s1 和 s2 都匹配完了，说明我们找到了一种匹配方案，返回 true
		if (i == -1 && j == -1) {
			return true;
		}
		// 如果 s1 匹配完了则只能用 s2 来匹配 s3
		if (i == -1) {
			return s2[j] == s3[i + j + 1] && dfs(i, j - 1, s1, s2, s3, memo);
		}
		// 如果 s2 匹配完了则只能用 s1 来匹配 s3
		if (j == -1) {
			return s1[i] == s3[i + j + 1] && dfs(i - 1, j, s1, s2, s3, memo);
		}
		if (memo[i][j] != null) {
			return memo[i][j];
		}
		// 如果 s1[i] 和 s3[i + j + 1] 相等，则我们可以选择用 s1[i] 来匹配 s3[i + j + 1]
		// 如果 s2[j] 和 s3[i + j + 1] 相等，则我们可以选择用 s2[j] 来匹配 s3[i + j + 1]
		// 只要其中一种选择能够匹配成功，我们就认为 s3 是 s1 和 s2 的交错字符串
		return memo[i][j] = (s2[j] == s3[i + j + 1] && dfs(i, j - 1, s1, s2, s3, memo))
				|| (s1[i] == s3[i + j + 1] && dfs(i - 1, j, s1, s2, s3, memo));
	}
}
