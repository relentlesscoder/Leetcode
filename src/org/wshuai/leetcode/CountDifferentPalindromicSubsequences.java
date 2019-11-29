package org.wshuai.leetcode;

/**
 * Created by Wei on 11/29/19.
 * #730 https://leetcode.com/problems/count-different-palindromic-subsequences/
 */
public class CountDifferentPalindromicSubsequences {
	// https://www.youtube.com/watch?v=UjiFFYU3EKM
	public int countPalindromicSubsequences(String S) {
		int mod = 1_000_000_007;
		int n = S.length();
		int[][] dp = new int[n][n];
		for(int i = 0; i < n; i++){
			dp[i][i] = 1;
		}
		char[] s = S.toCharArray();
		for(int len = 1; len <= n; len++){
			for(int i = 0; i + len < n; i++){
				long ans = 0L;
				int j = i + len;
				if(s[i] == s[j]){
					ans = dp[i + 1][j - 1] * 2;
					int l = i + 1;
					int r = j - 1;
					while (l <= r && s[l] != s[i]){
						++l;
					}
					while (l <= r && s[r] != s[i]) {
						--r;
					}
					if (l == r) {
						ans += 1;
					}else if (l > r) {
						ans += 2;
					}else{
						ans -= dp[l + 1][r - 1];
					}
				}else {
					ans = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
				}
				dp[i][j] = (int)((ans + mod) % mod);
			}
		}
		return dp[0][n - 1];
	}
}
