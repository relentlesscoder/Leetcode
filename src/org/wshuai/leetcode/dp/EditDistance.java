package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 09/02/2016.
 * #0072 https://leetcode.com/problems/edit-distance/
 */
public class EditDistance {

	// time O(m * n), space O(n)
	public int minDistance(String word1, String word2) {
		// 空间优化版 DP
		int m = word1.length(), n = word2.length();
		int[] dp = new int[n + 1];
		Arrays.setAll(dp, i -> i);
		for (int i = 0; i < m; i++) {
			int pre = dp[0];
			dp[0] = i + 1;
			for (int j = 0; j < n; j++) {
				int x = dp[j + 1];
				if (word1.charAt(i) == word2.charAt(j)) {
					dp[j + 1] = pre;
				} else {
					dp[j + 1] = 1 + Math.min(pre, Math.min(dp[j], dp[j + 1]));
				}
				pre = x;
			}
		}
		return dp[n];
	}

	// time O(m * n), space O(m * n)
	public int minDistanceDPWithGrid(String word1, String word2) {
		// 把记忆化搜索翻译成 DP
		int m = word1.length(), n = word2.length();
		int[][] dp = new int[m + 1][n + 1];
		Arrays.setAll(dp[0], i -> i);
		for (int i = 0; i < m; i++) {
			dp[i + 1][0] = i + 1;
			for (int j = 0; j < n; j++) {
				if (word1.charAt(i) == word2.charAt(j)) {
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					dp[i + 1][j + 1] = 1 + Math.min(dp[i][j],
							Math.min(dp[i + 1][j], dp[i][j + 1]));
				}
			}
		}
		return dp[m][n];
	}

	// time O(m * n), space O(m * n)
	public int minDistanceDFSWithMemorization(String word1, String word2) {
		// 记忆化搜索
		int m = word1.length(), n = word2.length();
		int[][] memo = new int[m + 1][n + 1];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return dfs(m - 1, n - 1, word1.toCharArray(), word2.toCharArray(), memo);
	}

	private int dfs(int i, int j, char[] s1, char[] s2, int[][] memo) {
		if (i == -1 && j == -1) { // 两个字符串都被完全匹配了
			return 0;
		}
		if (i == -1 || j == -1) { // 其中一个字符串已经被完全匹配了，剩下的另一个字符串的字符都要被删除
			return i == -1 ? j + 1 : i + 1;
		}
		if (memo[i][j] != -1) {
			return memo[i][j];
		}
		if (s1[i] == s2[j]) { // 两个字符匹配则不需要操作
			return memo[i][j] = dfs(i - 1, j - 1, s1, s2, memo);
		} else {
			// 三种情况都需要花费一次操作:
			// 1. 删除 s1[i] -> 相当于用 s1[i - 1] 来匹配 s2[j] -> f(i - 1, j)
			// 2. 增加 s2[j] 到 s1结尾 -> 新加的字符匹配了 s2[j] 则相当于用 s1[i] 来匹配 s2[j - 1] -> f(i, j - 1)
			// 3. 替换使得 s1[i] == s2[j] -> 则回到上面两个字符相同的情况 f(i - 1, j - 1)
			return memo[i][j] = 1 + Math.min(dfs(i - 1, j - 1, s1, s2, memo),
					Math.min(dfs(i - 1, j, s1, s2, memo), dfs(i, j - 1, s1, s2, memo)));
		}
	}
}
