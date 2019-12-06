package org.wshuai.leetcode;

/**
 * Created by Wei on 12/6/2019.
 * #903 https://leetcode.com/problems/valid-permutations-for-di-sequence/
 */
public class ValidPermutationsForDISequence {
	/*
	if(S[i-1] == 'D')
	dp[i][j] = dp[i-1][j] + dp[i-1][j+1] + ... + dp[i-1][i-1]

			if(S[i-1] == 'I')
	dp[i][j] = dp[i-1][0] + dp[i-1][1] + ... + dp[i-1][j-1]
	*/
	public int numPermsDISequence(String S) {
		int n = S.length();
		int mod = 1_000_000_007;
		int[][] dp = new int[n + 1][n + 1];
		dp[0][0] = 1;
		for(int i = 1; i <= n; i++){
			for(int j = 0; j <= i; j++){
				if(S.charAt(i - 1) == 'D'){
					for(int k = j; k <= i - 1; k++){
						dp[i][j] = dp[i][j] % mod + dp[i - 1][k] % mod;
					}
				}else{
					for(int k = 0; k <= j-1; k++){
						dp[i][j] = dp[i][j] % mod + dp[i-1][k] % mod;
					}
				}
			}
		}
		int res = 0;
		for(int i = 0; i <= n; i++)
			res = res % mod + dp[n][i] % mod;
		return res % mod;
	}
}
