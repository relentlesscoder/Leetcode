package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 11/11/2016.
 * #0132 https://leetcode.com/problems/palindrome-partitioning-ii/
 */
public class PalindromePartitioningII {

	// time O(n^2), space O(n)
	public int minCutDPWithExpand(String s) {
		// 核心思路：枚举每个位置作为回文中心，向两边扩展
		// 每找到一个回文 s[j..i]，立刻更新 dp[i+1] = min(dp[i+1], dp[j] + 1)
		// 不需要预计算 isPalin[][]，空间从 O(n²) 降到 O(n)
		int n = s.length();
		// dp[i+1] = s[0..i] 的最少回文分割段数
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0; // 空串，0 段
		for (int mid = 0; mid < n; mid++) {
			// 奇数长度回文：以 mid 为中心，向两边扩展
			for (int j = mid, i = mid; j >= 0 && i < n && s.charAt(j) == s.charAt(i); j--, i++) {
				// s[j..i] 是回文，前面 s[0..j-1] 需要 dp[j] 段，加上当前这段
				dp[i + 1] = Math.min(dp[i + 1], dp[j] + 1);
			}
			// 偶数长度回文：以 mid 和 mid+1 为中心，向两边扩展
			for (int j = mid, i = mid + 1; j >= 0 && i < n && s.charAt(j) == s.charAt(i); j--, i++) {
				dp[i + 1] = Math.min(dp[i + 1], dp[j] + 1);
			}
		}
		// dp[n] 是段数，切割次数 = 段数 - 1
		return dp[n] - 1;
	}

	// time O(n^2), space O(n^2)
	public int minCutDP(String s) {
		// 核心思路：dp[i+1] = s[0..i] 的最少回文分割段数
		// 枚举最后一段回文 s[j..i]，转移：dp[i+1] = min(dp[j] + 1)，其中 s[j..i] 是回文
		// 最终答案 = dp[n] - 1 (段数 - 1 = 切割次数)
		int n = s.length();
		// O(n²) 预计算所有回文子串，之后每次查询 O(1)
		boolean[][] isPalin = new boolean[n][n];
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				isPalin[i][j] = s.charAt(i) == s.charAt(j)
						&& (j - i <= 2 || isPalin[i + 1][j - 1]);
			}
		}
		// dp[i+1] = s[0..i] 的最少回文分割段数
		// dp[0] = 0 (空串，0 段)
		int[] dp = new int[n + 1];
		for (int i = 0; i < n; i++) {
			int res = Integer.MAX_VALUE;
			// 枚举最后一段回文的起点 j：s[j..i] 是回文
			for (int j = i; j >= 0; j--) {
				if (isPalin[j][i]) {
					// s[j..i] 是回文，前面 s[0..j-1] 需要 dp[j] 段，加上当前这段 = dp[j] + 1
					res = Math.min(res, 1 + dp[j]);
				}
			}
			dp[i + 1] = res;
		}
		// dp[n] 是段数，切割次数 = 段数 - 1
		return dp[n] - 1;
	}

	// time O(n^2), space O(n^2)
	public int minCutDFSWithMemoization(String s) {
		// dfs(i) = s[0..i] 的最少切割次数 (从右往左递归)
		int n = s.length();
		// O(n²) 预计算所有回文子串
		boolean[][] isPalin = new boolean[n][n];
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				isPalin[i][j] = s.charAt(i) == s.charAt(j)
						&& (j - i <= 2 || isPalin[i + 1][j - 1]);
			}
		}
		int[] memo = new int[n];
		Arrays.fill(memo, -1);
		return dfs(n - 1, isPalin, memo) - 1;
	}

	// dfs(i) = s[0..i] 的最少切割次数
	private int dfs(int i, boolean[][] isPalin, int[] memo) {
		if (i == -1) {
			return 0; // s[0..i] 整体是回文，不需要切
		}
		if (memo[i] != -1) {
			return memo[i];
		}
		int res = Integer.MAX_VALUE;
		// 枚举最后一段回文的起点 j：s[j..i] 是回文
		for (int j = i; j >= 0; j--) {
			if (isPalin[j][i]) {
				// 在 j 处切一刀，s[j..i] 是回文，递归求 s[0..j-1] 的最少切割次数
				res = Math.min(res, 1 + dfs(j - 1, isPalin, memo));
			}
		}
		return memo[i] = res;
	}
}
