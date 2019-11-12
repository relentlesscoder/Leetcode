package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2019.
 * #1223 https://leetcode.com/problems/dice-roll-simulation/
 */
public class DiceRollSimulation {

	// https://www.youtube.com/watch?v=3JOZcD-BRLE&list=PLLuMmzMTgVK7vEbeHBDD42pqqG36jhuOr&index=2
	public int dieSimulator(int n, int[] rollMax) {
		int maxRoll = 15;
		int mod = 1_000_000_007;

		// dp[i][j][k] is the sequence of length i end with number k of j
		int[][][] dp = new int[n + 1][6][maxRoll + 1];

		for(int j = 0; j < 6; j++){
			dp[1][j][1] = 1;
		}

		for(int i = 2; i <= n; i++){
			for(int j = 0; j < 6; j++){
				// p is the previous dice roll number
				for(int p = 0; p < 6; p++){
					for(int k = 1; k <= rollMax[p]; k++){
						// if the current number j is different than the previous p
						// the total number of ways (dp[i][j][1]) is the sum
						// of all sequence of length i - 1 ending with any number
						// of dice roll value p
						if(p != j){
							dp[i][j][1] = (dp[i][j][1] + dp[i - 1][p][k]) % mod;
						// if the current number j is the same as the previous p
						// simply copy the previous value dp[i - 1][j][k]
						}else if(k < rollMax[p]){
							dp[i][j][k + 1] = dp[i - 1][j][k];
						}
					}
				}
			}
		}

		int res = 0;
		for(int j = 0; j < 6; j++){
			for(int k = 1; k <= rollMax[j]; k++){
				// the final result is just the sum of all sequence of length n
				res = (res + dp[n][j][k]) % mod;
			}
		}
		return res;
	}
}
