package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #338 https://leetcode.com/problems/counting-bits/
 */
public class CountingBits {

	// DP
	public int[] countBits(int num) {
		int[] dp = new int[num + 1];
		dp[0] = 0;
		for(int i = 1; i <= num; i++){
			dp[i] = dp[i >> 1] + (i % 2);
		}
		return dp;
	}

	public int[] countBitsHammingDistance(int num) {
		if (num < 0) {
			return new int[0];
		}

		int[] res = new int[num + 1];
		NumberOf1Bits nb = new NumberOf1Bits();
		for (int i = 0; i <= num; i++) {
			res[i] = nb.hammingWeight(i);
		}
		return res;
	}
}
