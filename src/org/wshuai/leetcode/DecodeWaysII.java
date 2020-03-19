package org.wshuai.leetcode;

/**
 * Created by Wei on 11/26/2019.
 * #0639 https://leetcode.com/problems/decode-ways-ii/
 */
public class DecodeWaysII {

	private static final int MOD = 1_000_000_007;

	// time O(n), space O(n)
	public int numDecodings(String s) {
		int n = s.length();
		long[] dp = new long[n + 1];
		dp[0] = 1;
		dp[1] = s.charAt(0) == '0' ? 0 : s.charAt(0) == '*' ? 9 : 1;
		for (int i = 2; i <= n; i++) {
			char cur = s.charAt(i - 1), prev = s.charAt(i - 2);
			if (cur == '0' && (prev != '1' && prev != '2' && prev != '*')) {
				return 0;
			}
			// xxxxx6
			if (cur >= '1' && cur <= '9') {
				dp[i] += dp[i - 1];
			}
			// xxxx22
			if ((prev == '1' && cur >= '0' && cur <= '9')
					|| (prev == '2' && cur >= '0' && cur <= '6')) {
				dp[i] += dp[i - 2];
			}
			// xxxxx[1-9]
			if (cur == '*') {
				dp[i] += 9 * dp[i - 1];
			}
			// xxxx1[1-9]
			if (cur == '*' && prev == '1') {
				dp[i] += 9 * dp[i - 2];
			}
			// xxxx2[1-6]
			if (cur == '*' && prev == '2') {
				dp[i] += 6 * dp[i - 2];
			}
			// xxxx[1-2][1-9]
			if (cur == '*' && prev == '*') {
				dp[i] += 15 * dp[i - 2];
			}
			// xxxx19
			if (prev == '*' && cur >= '0' && cur <= '9') {
				dp[i] += dp[i - 2];
			}
			// xxxx25
			if (prev == '*' && cur >= '0' && cur <= '6') {
				dp[i] += dp[i - 2];
			}
			dp[i] %= MOD;
		}
		return (int) dp[n];
	}
}
