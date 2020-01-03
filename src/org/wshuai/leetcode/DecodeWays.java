package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #91 https://leetcode.com/problems/decode-ways/
 */
public class DecodeWays {
	public int numDecodings(String s) {
		if(s == null || s.length() == 0 || s.charAt(0) == '0'){
			return 0;
		}
		int N = s.length();
		int[] dp = new int[N + 1];
		dp[0] = 1;
		dp[1] = 1;
		for(int i = 2; i <= N; i++){
			char cur = s.charAt(i - 1);
			char prev = s.charAt(i - 2);
			if(cur < '0' || cur > '9'){
				return 0;
			}
			//xxxxx4
			if(cur >= '1' && cur <= '9'){
				dp[i] += dp[i - 1];
			}
			//xxxx16
			if(cur >= '0' && cur <= '9' && prev == '1'){
				dp[i] += dp[i - 2];
			}
			//xxxx25
			if(cur >= '0' && cur <= '6' && prev == '2'){
				dp[i] += dp[i - 2];
			}
		}
		return dp[N];
	}
}
