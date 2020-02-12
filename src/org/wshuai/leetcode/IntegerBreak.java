package org.wshuai.leetcode;

/**
 * Created by Wei on 11/02/2016.
 * #0343 https://leetcode.com/problems/integer-break/
 */
public class IntegerBreak {
	// time O(n)
	// https://leetcode.com/problems/integer-break/discuss/80694/Java-DP-solution
	public int integerBreak(int n) {
		int[] dp = new int[n + 1];
		dp[1] = 1;
		for(int i = 2; i <= n; i++){
			for(int j = 1; j <= i / 2; j++){
				dp[i] = Math.max(dp[i], Math.max(j, dp[j])
						* Math.max(i - j, dp[i - j]));
			}
		}
		return dp[n];
	}
}
