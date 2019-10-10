package org.wshuai.algorithm.dynamicProgramming;

import java.util.Arrays;

public class CoinChange {

	public int coinChange(int[] coins, int target){
		int[] dp = new int[target + 1];

		dp[0] = 1;
		for(int i = 1; i <= target; i++){
			for(int j = 0; j < coins.length; j++){
				if(coins[j] <= i){
					dp[i] += dp[i - coins[j]];
				}
			}
		}

		return dp[target];
	}
}
