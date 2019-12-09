package org.wshuai.leetcode;

/**
 * Created by Wei on 12/9/2019.
 * #1269 https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/
 */
public class NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {
	private static final int mod = 1_000_000_007;

	public int numWays(int steps, int arrLen) {
		int[][] dp = new int[2][Math.min(steps, arrLen) + 1];
		int prev = 0;
		int cur = 1;
		dp[prev][0] = 1;
		for(int i = 1; i <= steps; i++){
			for(int j = 0; j < Math.min(steps, arrLen); j++){
				dp[cur][j] = 0;
				if(dp[prev][j] > 0){
					dp[cur][j] = (dp[cur][j] + dp[prev][j]) % mod;
				}
				if(j > 0 && dp[prev][j - 1] > 0){
					dp[cur][j] = (dp[cur][j] + dp[prev][j - 1]) % mod;
				}
				if(j < arrLen - 1 && dp[prev][j + 1] > 0){
					dp[cur][j] = (dp[cur][j] + dp[prev][j + 1]) % mod;
				}
			}
			prev = cur;
			cur = 1 - prev;
		}
		return dp[prev][0];
	}
}
