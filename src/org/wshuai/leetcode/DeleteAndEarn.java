package org.wshuai.leetcode;

/**
 * Created by Wei on 10/10/2019.
 * #0740 https://leetcode.com/problems/delete-and-earn/
 */
public class DeleteAndEarn {
	// time O(n)
	public int deleteAndEarn(int[] nums) {
		int[] sum = new int[10_001];
		for(int n: nums){
			sum[n] += n;
		}
		int[] dp = new int[10_001];
		dp[1] = sum[1];
		for(int i = 2; i <= 10_000; i++){
			// the max of use num i or not use
			dp[i] = Math.max(sum[i] + dp[i - 2], dp[i - 1]);
		}
		return dp[10_000];
	}
}
