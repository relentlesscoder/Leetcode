package org.wshuai.leetcode;

/**
 * Created by Wei on 11/26/19.
 * #639 https://leetcode.com/problems/decode-ways-ii/
 */
public class DecodeWaysII {
	public int numDecodings(String s) {
		long mod = 1_000_000_007L;
		int N = s.length();
		char[] arr = s.toCharArray();
		long[] dp = new long[N + 1];
		dp[0] = 1L;
		if(arr[0] == '0'){
			return 0;
		}else if(arr[0] == '*'){
			dp[1] = 9L;
		}else{
			dp[1] = 1L;
		}
		for(int i = 2; i <= N; i++){
			char curr = arr[i - 1];
			char last = arr[i - 2];
			if(curr == '0' && (last != '1' && last != '2' && last != '*')){
				return 0;
			}
			// xxxxx6
			if(curr >= '1' && curr <= '9'){
				dp[i] = dp[i] + dp[i - 1];
			}
			// xxxx22
			if((last == '1' && curr >= '0' && curr <= '9')
					|| (last == '2' && curr >= '0' && curr <= '6')){
				dp[i] += dp[i-2];
			}
			// xxxxx[1-9]
			if(curr == '*'){
				dp[i] += 9 * dp[i - 1];
			}
			// xxxx1[1-9]
			if(last == '1' && curr == '*'){
				dp[i] += 9 * dp[i - 2];
			}
			// xxxx2[1-6]
			if(last == '2' && curr == '*'){
				dp[i] += 6 * dp[i - 2];
			}
			// xxxx[1-2][1-9]
			if(last == '*' && curr == '*'){
				dp[i] += 15 * dp[i - 2];
			}
			// xxxx19
			if(last == '*' && curr >= '0' && curr <= '9'){
				dp[i] += dp[i - 2];
			}
			// xxxx25
			if(last == '*' && curr >= '0' && curr <= '6'){
				dp[i] += dp[i - 2];
			}
			dp[i] %= mod;
		}
		return (int)dp[N];
	}
}
