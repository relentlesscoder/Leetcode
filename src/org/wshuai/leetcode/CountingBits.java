package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0338 https://leetcode.com/problems/counting-bits/
 */
public class CountingBits {
	// time O(n)
	public int[] countBits(int num) {
		int[] dp = new int[num + 1];
		for(int i = 1; i <= num; i++){
			dp[i] += dp[i >> 1] + (i % 2);
		}
		return dp;
	}
}
