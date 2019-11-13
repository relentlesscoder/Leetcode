package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/19.
 * #634 https://leetcode.com/problems/find-the-derangement-of-an-array/
 */
public class FindTheDerangementOfAnArray {

	// optimize the space to O(1)
	public int findDerangement(int n) {
		if(n <= 1){
			return 0;
		}
		long mod = 1_000_000_007;
		long dp1 = 0;
		long dp2 = 1;
		for(int i = 3; i <= n; i++){
			long cur = ((i - 1) * (dp1 + dp2)) % mod;
			dp1 = dp2;
			dp2 = cur;
		}
		return (int)dp2;
	}

	// https://leetcode.com/problems/find-the-derangement-of-an-array/discuss/104981/If-you-don't-understand
	public int findDerangementDP(int n) {
		if(n <= 1){
			return 0;
		}
		long[] dp = new long[n + 1];
		long mod = 1_000_000_007;
		dp[2] = 1;
		for(int i = 3; i <= n; i++){
			dp[i] = ((i - 1) * (dp[i - 1] + dp[i - 2])) % mod;
		}
		return (int)dp[n];
	}
}
