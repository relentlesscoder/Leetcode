package org.wshuai.leetcode;

/**
 * Created by Wei on 11/26/2019.
 * #0629 https://leetcode.com/problems/k-inverse-pairs-array/
 */
public class KInversePairsArray {
	// https://leetcode.com/problems/k-inverse-pairs-array/discuss/104815/Java-DP-O(nk)-solution
	public int kInversePairs(int n, int k) {
		int mod = 1_000_000_007;
		//max possible inverse pairs (array is in descending order)
		int max = n*(n - 1)/2;
		if(k > max || k < 0){
			return 0;
		}
		if(k == 0 || k == max){
			return 1;
		}
		long[][] dp = new long[n + 1][k + 1];
		dp[2][0] = 1;
		dp[2][1] = 1;
		for(int i = 3; i <= n; i++){
			dp[i][0] = 1;
			int cur = i*(i-1)/2;
			for(int j = 1; j <= Math.min(k, cur); j++){
				dp[i][j] = dp[i][j-1] + dp[i-1][j];
				if(j >= i){
					dp[i][j] -= dp[i-1][j-i];
				}
				dp[i][j] = (dp[i][j] + mod) % mod;
			}
		}
		return (int)dp[n][k];
	}
}
