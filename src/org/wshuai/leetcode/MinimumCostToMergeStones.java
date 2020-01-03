package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/10/2019.
 * #1000 https://leetcode.com/problems/minimum-cost-to-merge-stones/
 */
public class MinimumCostToMergeStones {
	// https://www.youtube.com/watch?v=FabkoUzs64o
	public int mergeStones(int[] stones, int K) {
		int n = stones.length;
		if((n - 1) % (K - 1) != 0){
			return -1;
		}
		int[] prefix = new int[n + 1];
		for(int i = 1; i <= n; i++){
			prefix[i] = prefix[i - 1] + stones[i - 1];
		}

		int inf = 1_000_000_000;
		int[][][] dp = new int[n][n][K + 1];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				Arrays.fill(dp[i][j], inf);
			}
		}
		for(int i = 0; i < n; i++){
			dp[i][i][1] = 0;
		}
		for(int l = 2; l <= n; l++){
			for(int i = 0; i + l <= n; i++){
				int j = i + l - 1;
				for(int k = 2; k <= K; k++){
					for(int m = i; m < j; m += K - 1){
						dp[i][j][k] = Math.min(dp[i][j][k], dp[i][m][1] + dp[m + 1][j][k - 1]);
					}
				}
				// we can merge K piles into 1, the cost is just the sum of {i ... j}
				dp[i][j][1] = dp[i][j][K] + prefix[j + 1] - prefix[i];
			}
		}
		return dp[0][n - 1][1];
	}
}
