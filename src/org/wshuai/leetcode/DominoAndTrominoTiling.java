package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2019.
 * #790 https://leetcode.com/problems/domino-and-tromino-tiling/
 */
public class DominoAndTrominoTiling {
	public int numTilings(int N) {
		int MOD = 1_000_000_007;
		int[] dp = new int[1_001];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 5;
		for(int i = 4; i <= N; i++){
			dp[i] = ((2 * dp[i - 1]) % MOD + dp[i - 3] % MOD) % MOD;
		}
		return dp[N];
	}
}
