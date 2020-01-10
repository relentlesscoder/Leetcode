package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0070 https://leetcode.com/problems/climbing-stairs/
 */
public class ClimbingStairs {
	// time O(n), space O(1)
	public int climbStairsConstantSpace(int n) {
		int s1 = 1, s2 = 1;
		for(int i = 2; i <= n; i++){
			int temp = s2;
			s2 += s1;
			s1 = temp;
		}
		return s2;
	}

	// time O(n), space O(n)
	public int climbStairs(int n) {
		int[] dp = new int[n + 1];
		dp[0] = dp[1] = 1;
		for(int i = 2; i <= n; i++){
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}
}
