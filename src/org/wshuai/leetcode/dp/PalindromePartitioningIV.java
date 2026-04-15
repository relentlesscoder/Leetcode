package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 03/31/2026.
 * #1745
 * https://leetcode.com/problems/palindrome-partitioning-iv/
 */
public class PalindromePartitioningIV {

	// time O(n^2), space O(n)
	public boolean checkPartitioningDPWithCentralExpansion(String s) {
		// 核心思路: 划分型DP + 中心扩展法判断回文
		// dp[i][j] = 前j个字符能否被划分成恰好i个回文子串
		// 对每个中心点扩展出所有回文s[l..r], 若dp[i][l]为true则dp[i+1][r+1]也为true
		char[] sc = s.toCharArray();
		int n = sc.length;
		// dp[i][j]: 前j个字符能否划分成i个回文串
		boolean[][] dp = new boolean[4][n + 1];
		dp[0][0] = true; // 0个字符, 0个回文串
		// 每轮增加一个回文段
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < n; j++) {
				// 奇数长度回文: 以j为中心向两边扩展
				int l = j, r = j;
				while (l >= 0 && r < n && sc[l] == sc[r]) {
					// s[l..r]是回文, 若前l个字符能划分成i段, 则前r+1个字符能划分成i+1段
					dp[i + 1][r + 1] = dp[i + 1][r + 1] || dp[i][l];
					l--;
					r++;
				}
				// 偶数长度回文: 以j和j+1为中心向两边扩展
				l = j;
				r = j + 1;
				while (l >= 0 && r < n && sc[l] == sc[r]) {
					dp[i + 1][r + 1] = dp[i + 1][r + 1] || dp[i][l];
					l--;
					r++;
				}
			}
		}
		// 整个字符串能否划分成3个回文串
		return dp[3][n];
	}
}
