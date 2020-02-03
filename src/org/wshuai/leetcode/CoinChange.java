package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2016.
 * #0322 https://leetcode.com/problems/coin-change/
 */
public class CoinChange {
	// time O(len(coins)*amount)
	public int coinChange(int[] coins, int amount) {
		int[] dp = new int[amount + 1];
		dp[0] = 0;
		for(int i = 1; i <= amount; i++){
			dp[i] = Integer.MAX_VALUE;
			for(int j = 0; j < coins.length; j++){
				if(i >= coins[j] && dp[i - coins[j]] != Integer.MAX_VALUE){
					dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
				}
			}
		}
		return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
	}
}
