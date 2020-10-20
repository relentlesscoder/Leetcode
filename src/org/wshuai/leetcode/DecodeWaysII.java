package org.wshuai.leetcode;

/**
 * Created by Wei on 11/26/2019.
 * #0639 https://leetcode.com/problems/decode-ways-ii/
 */
public class DecodeWaysII {

	private static final int MOD = 1_000_000_007;

	// time O(n), space O(n)
	public int numDecodings(String s) {
		if(s == null || s.length() == 0 || s.charAt(0) == '0'){
			return 0;
		}
		int n = s.length();
		long[] dp = new long[n + 1];
		dp[0] = 1;
		dp[1] = s.charAt(0) == '*' ? 9 : 1;
		for(int i = 2; i <= n; i++){
			char cur = s.charAt(i - 1), prev = s.charAt(i - 2);
			if(cur == '0' && (prev != '1' && prev != '2' && prev != '*')){
				return 0;
			}
			if(cur >= '1' && cur <= '9'){
				dp[i] = (dp[i] + dp[i - 1]) % MOD;
			}
			if((prev == '1' && cur >= '0' && cur <= '9')
					|| (prev == '2' && cur >= '0' && cur <= '6')){
				dp[i] = (dp[i] + dp[i - 2]) % MOD;
			}
			// cur is '*'
			if(cur == '*'){
				dp[i] = (dp[i] + 9 * dp[i - 1] % MOD) % MOD;
			}
			if(cur == '*' && prev == '1'){
				dp[i] = (dp[i] + 9 * dp[i - 2] % MOD) % MOD;
			}
			if(cur == '*' && prev == '2'){
				dp[i] = (dp[i] + 6 * dp[i - 2] % MOD) % MOD;
			}
			// prev is '*'
			if(prev == '*' && cur >= '0' && cur <= '9'){
				dp[i] = (dp[i] + dp[i - 2]) % MOD;
			}
			if(prev == '*' && cur >= '0' && cur <= '6'){
				dp[i] = (dp[i] + dp[i - 2]) % MOD;
			}
			// both cur and prev are '*'
			if(cur == '*' && prev == '*'){
				dp[i] = (dp[i] + 15 * dp[i - 2] % MOD) % MOD;;
			}
		}
		return (int)dp[n];
	}
}
