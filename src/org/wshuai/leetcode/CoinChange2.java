package org.wshuai.leetcode;

/**
 * Created by Wei on 09/24/2019.
 * #0518 https://leetcode.com/problems/coin-change-2/
 */
public class CoinChange2 {
	// time O(m*n), space O(m)
	// Knapsack, see https://leetcode.com/problems/coin-change-2/discuss/99222/Video-explaining-how-dynamic-programming-works-with-the-Coin-Change-problem
	public int change(int amount, int[] coins) {
		int[] dp = new int[amount + 1];
		dp[0] = 1;
		for(int i = 0; i < coins.length; i++){
			for(int j = 1; j <= amount; j++){
				if(j >= coins[i]){
					dp[j] += dp[j - coins[i]];
				}
			}
		}
		return dp[amount];
	}

	// time O(m*n), space O(m*n)
	public int changeDP(int amount, int[] coins) {
		int[][] dp = new int[coins.length + 1][amount + 1];
		dp[0][0] = 1;
		for(int i = 1; i <= coins.length; i++){
			for(int j = 0; j <= amount; j++){
				if(j >= coins[i-1]){
					dp[i][j] = dp[i-1][j] + dp[i][j - coins[i-1]];
				}else{
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		return dp[coins.length][amount];
	}
}
