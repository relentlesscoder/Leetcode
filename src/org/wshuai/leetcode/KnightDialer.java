package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/7/2019.
 * #935 https://leetcode.com/problems/knight-dialer/
 */
public class KnightDialer {
	public int knightDialer(int N) {
		int MOD = 1_000_000_007;
		int[][] move = new int[][]{
				{4, 6},{6, 8},{7, 9},{4, 8},{3, 9, 0},
				{},{1, 7, 0},{2, 6},{1, 3},{2, 4}
		};

		int[][] dp = new int[N + 1][10];
		Arrays.fill(dp[1], 1);
		for(int i = 2; i < dp.length; i++){
			for(int j = 0; j < dp[0].length; j++){
				int[] m = move[j];
				for(int num: m){
					dp[i][j] += dp[i - 1][num];
					dp[i][j] %= MOD;
				}
			}
		}
		int count = 0;
		for(int i = 0; i < dp[0].length; i++){
			count += dp[N][i];
			count %= MOD;
		}
		return count;
	}
}
