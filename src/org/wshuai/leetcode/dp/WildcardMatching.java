package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 08/30/2016.
 * #0044 https://leetcode.com/problems/wildcard-matching/
 */
public class WildcardMatching {

	// time O(m * n), space O(n)
	public boolean isMatch(String s, String p) {
		// 空间优化版 DP
		int m = s.length(), n = p.length();
		boolean[] dp = new boolean[n + 1];
		dp[0] = true;
		for (int j = 0; j < n; j++) {
			dp[j + 1] = p.charAt(j) == '*' ? dp[j] : false;
		}
		for (int i = 0; i < m; i++) {
			boolean pre = dp[0];
			dp[0] = false;
			for (int j = 0; j < n; j++) {
				boolean x = dp[j + 1];
				if (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j)) {
					dp[j + 1] = pre;
				} else if (p.charAt(j) == '*') {
					dp[j + 1] = dp[j + 1] || pre || dp[j];
				} else {
					dp[j + 1] = dp[j + 1] = false;
				}
				pre = x;
			}
		}
		return dp[n];
	}

	// time O(m * n), space O(m * n)
	public boolean isMatchDPWithGrid(String s, String p) {
		// 把记忆化搜索翻译成 DP
		int m = s.length(), n = p.length();
		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[0][0] = true;
		for (int j = 0; j < n; j++) {
			dp[0][j + 1] = p.charAt(j) == '*' ? dp[0][j] : false;
		}
		for (int i = 0; i < m; i++) {
			dp[i + 1][0] = false;
			for (int j = 0; j < n; j++) {
				if (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j)) {
					dp[i + 1][j + 1] = dp[i][j];
				} else if (p.charAt(j) == '*') {
					dp[i + 1][j + 1] = dp[i][j + 1] || dp[i][j] || dp[i + 1][j];
				}
			}
		}
		return dp[m][n];
	}

	// time O(m * n), space O(m * n)
	public boolean isMatchDFSWithMemorization(String s, String p) {
		// 记忆化搜索
		int m = s.length(), n = p.length();
		Boolean[][] memo = new Boolean[m][n];
		return dfs(m - 1, n - 1, s.toCharArray(), p.toCharArray(), memo);
	}

	private boolean dfs(int i, int j, char[] s, char[] p, Boolean[][] memo) {
		// 如果两个字符串都匹配完了，说明我们找到了一种匹配方案，返回 true
		if (i == -1 && j == -1) {
			return true;
		}
		// 如果模式串已经匹配完了但源字符串还没有匹配完，说明当前的匹配方案不合法，返回 false
		if (j == -1) {
			return false;
		}
		// 如果源字符串已经匹配完了但模式串还没有匹配完，两种情况:
		// 1. 模式串当前的字符都是 '*' 继续匹配模式串的下一个字符
		// 2. 模式串剩余的字符不全是 '*'，说明当前的匹配方案不合法，返回 false
		if (i == -1) {
			return p[j] == '*' ? dfs(i, j - 1, s, p, memo) : false;
		}
		if (memo[i][j] != null) {
			return memo[i][j];
		}
		// 模式串当前的字符如果是 '?' 或者和源字符串当前的字符相同，则继续匹配两个字符串的下一个字符
		if (p[j] == '?' || s[i] == p[j]) {
			return memo[i][j] = dfs(i - 1, j - 1, s, p, memo);
		} else if (p[j] == '*') {
			// 模式串当前的字符如果是 '*'，则有三种匹配方案：
			// 1. '*' 匹配源字符串当前的字符，保留模式串的当前字符并继续匹配源字符串的下一个字符
			// 2. '*' 匹配源字符串当前的字符，不保留模式串的当前字符并继续匹配源字符串的下一个字符
			// 3. '*' 不匹配任何字符 - 空字符，继续匹配源字符串的当前字符和模式串的下一个字符
			return memo[i][j] = dfs(i - 1, j, s, p, memo) || dfs(i - 1, j - 1, s, p, memo) || dfs(i, j - 1, s, p, memo);
		}
		return memo[i][j] = false;
	}
}
