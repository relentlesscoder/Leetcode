package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #0091 https://leetcode.com/problems/decode-ways/
 */
public class DecodeWays {
	// time O(n), space O(n)
	public int numDecodings(String s) {
		if(s == null || s.isEmpty()){
			return 0;
		}
		int n = s.length();
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = s.charAt(0) == '0' ? 0 : 1;
		for(int i = 2; i <= n; i++){
			char cur = s.charAt(i - 1);
			char prev = s.charAt(i - 2);
			if(cur >= '1' && cur <= '9'){
				dp[i] += dp[i - 1];
			}
			if(prev == '1' || (prev == '2' && cur >= '0' && cur <= '6')){
				dp[i] += dp[i - 2];
			}
		}
		return dp[n];
	}
}
