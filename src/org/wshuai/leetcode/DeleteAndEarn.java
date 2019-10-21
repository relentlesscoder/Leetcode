package org.wshuai.leetcode;

/**
 * Created by Wei on 10/10/2019.
 * #740 https://leetcode.com/problems/delete-and-earn/
 */
public class DeleteAndEarn {
	public int deleteAndEarn(int[] nums) {
		int[] count = new int[10_001];
		for(int n: nums){
			count[n] += n;
		}
		int[] dp = new int[10_001];
		dp[1] = count[1];
		for(int i = 2; i <= 10_000; i++){
			dp[i] = Math.max(count[i] + dp[i - 2], dp[i - 1]);
		}
		return dp[10_000];
	}
}
