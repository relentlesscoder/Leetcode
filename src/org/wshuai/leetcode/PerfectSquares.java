package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/27/2016.
 * #0279 https://leetcode.com/problems/perfect-squares/
 */
public class PerfectSquares {
	// time O(n^2), space O(n)
	public int numSquares(int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for(int i = 1; i <= n; i++){
			for(int j = 1; j*j <= i; j++){
				dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
			}
		}
		return dp[n];
	}
}
