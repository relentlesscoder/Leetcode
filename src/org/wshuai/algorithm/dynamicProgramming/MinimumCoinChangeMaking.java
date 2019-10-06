package org.wshuai.algorithm.dynamicProgramming;


import java.util.Arrays;

public class MinimumCoinChangeMaking {

	public int minimumCoinChange(int[] coins, int target){
		if (coins == null || coins.length == 0) {
			return -1;
		}

		int N = coins.length;
		int[] dp = new int[target + 1];

		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for(int i = 1; i <= target; i++){
			for(int j = 0; j < N; j++){
				if(i >= coins[j]){
					int count = dp[i - coins[j]];
					if(count != Integer.MAX_VALUE && count + 1 < dp[i]){
						dp[i] = count + 1;
					}
				}
			}
		}

		return dp[target] == Integer.MAX_VALUE ? -1 : dp[target];
	}
}
