package org.wshuai.leetcode;

/**
 * Created by Wei on 11/29/2019.
 * #0730 https://leetcode.com/problems/count-different-palindromic-subsequences/
 */
public class CountDifferentPalindromicSubsequences {

	private static final long MOD = 1_000_000_007;

	// time O(n^2), space O(n^2)
	// https://www.youtube.com/watch?v=UjiFFYU3EKM
	public int countPalindromicSubsequences(String S) {
		int n = S.length();
		char[] s = S.toCharArray();
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			dp[i][i] = 1;
		}
		for (int len = 1; len < n; len++) {
			for (int i = 0; i + len < n; i++) {
				long count = 0L;
				int j = i + len;
				if (s[i] == s[j]) {
					count = dp[i + 1][j - 1] << 1;
					int l = i + 1, r = j - 1;
					while (l <= r && s[l] != s[i]) {
						l++;
					}
					while (l <= r && s[r] != s[i]) {
						r--;
					}
					if (l == r) {
						count += 1;
					} else if (l > r) {
						count += 2;
					} else {
						count -= dp[l + 1][r - 1];
					}
				} else {
					count = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
				}
				dp[i][j] = (int) ((count + MOD) % MOD);
			}
		}
		return dp[0][n - 1];
	}
}
