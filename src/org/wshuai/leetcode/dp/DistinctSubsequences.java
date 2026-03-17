package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 02/06/2017.
 * #0115 https://leetcode.com/problems/distinct-subsequences/
 */
public class DistinctSubsequences {

	// time O(m * n), space O(n)
	public int numDistinct(String s, String t) {
		// 空间优化版 DP
		int m = s.length(), n = t.length();
		int[] dp = new int[n + 1];
		dp[0] = 1;
		for (int i = 0; i < m; i++) {
			int pre = dp[0];
			dp[0] = 1;
			for (int j = 0; j < n; j++) {
				int x = dp[j + 1];
				if (s.charAt(i) == t.charAt(j)) {
					dp[j + 1] += pre;
				}
				pre = x;
			}
		}
		return dp[n];
	}

	// time O(m * n), space O(m * n)
	public int numDistinctDPWithGrid(String s, String t) {
		// 把记忆化搜索翻译成 DP
		int m = s.length(), n = t.length();
		int[][] dp = new int[m + 1][n + 1];
		dp[0][0] = 1;
		for (int i = 0; i < m; i++) {
			dp[i + 1][0] = 1;
			for (int j = 0; j < n; j++) {
				dp[i + 1][j + 1] = dp[i][j + 1];
				if (s.charAt(i) == t.charAt(j)) {
					dp[i + 1][j + 1] += dp[i][j];
				}
			}
		}
		return dp[m][n];
	}

	// time O(m * n), space O(m * n)
	public int numDistinctDFSWithMemorization(String s, String t) {
		// 记忆化搜索
		int m = s.length(), n = t.length();
		int[][] memo = new int[m + 1][n + 1];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return dfs(m - 1, n - 1, s.toCharArray(), t.toCharArray(), memo);
	}

	private int dfs(int i, int j, char[] s, char[] t, int[][] memo) {
		if (j == -1) { // t 先为空，则匹配方案加 1
			return 1;
		}
		if (i == -1) { // s 先为空，则无法匹配
			return 0;
		}
		if (memo[i][j] != -1) {
			return memo[i][j];
		}
		// 总是可以选择 s[i] 不匹配 t[j]
		int res = dfs(i - 1, j, s, t, memo);
		if (s[i] == t[j]) { // 如果 s[i] 和 t[j] 相同，则还可以选择 s[i] 匹配 t[j]
			res += dfs(i - 1, j - 1, s, t, memo);
		}
		return memo[i][j] = res;
	}
}
