package org.wshuai.leetcode;

/**
 * Created by Wei on 12/5/2019.
 * #920 https://leetcode.com/problems/number-of-music-playlists/
 */
public class NumberOfMusicPlaylists {
	// dp[i][j] denotes played total i songs with j distinct
	public int numMusicPlaylists(int N, int L, int K) {
		int mod = 1_000_000_007;
		long[][] dp = new long[L + 1][N + 1];
		dp[0][0] = 1L;
		for(int i = 1; i <= L; i++){
			for(int j = 1; j <= N; j++){
				dp[i][j] = (dp[i - 1][j - 1] * (N - j + 1)) % mod;
				if(j > K){
					dp[i][j] = (dp[i][j] + (dp[i - 1][j] * (j - K)) % mod) % mod;
				}
			}
		}
		return (int)dp[L][N];
	}
}
