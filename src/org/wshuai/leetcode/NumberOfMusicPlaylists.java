package org.wshuai.leetcode;

/**
 * Created by Wei on 12/05/2019.
 * #0920 https://leetcode.com/problems/number-of-music-playlists/
 */
public class NumberOfMusicPlaylists {

	private static final int MOD = 1_000_000_007;

	// time O(L*N), space O(L*N)
	public int numMusicPlaylists(int N, int L, int K) {
		long[][] dp = new long[L + 1][N + 1]; // dp[i][j] denotes played total i songs with j distinct
		dp[0][0] = 1;
		for(int i = 1; i <= L; i++){
			for(int j = 1; j <= N; j++){
				dp[i][j] = (dp[i - 1][j - 1] * (N - j + 1)) % MOD;
				if(j > K){
					dp[i][j] = (dp[i][j] + (dp[i - 1][j] * (j - K)) % MOD) % MOD;
				}
			}
		}
		return (int)dp[L][N];
	}
}
