package org.wshuai.leetcode;

/**
 * Created by Wei on 10/20/2020.
 * #1621 https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments/
 */
public class NumberOfSetsOfKNonOverlappingLineSegments {

	private static final int MOD = 1_000_000_007;

	// time O(4*n*k), space O(2*n*k)
	public int numberOfSets(int n, int k) {
		return dfs(0, k, 1, n, new Integer[n + 1][k + 1][2]);
	}

	private int dfs(int i, int k, int start, int n, Integer[][][] dp){
		if(dp[i][k][start] != null){
			return dp[i][k][start];
		}
		if(k == 0){
			return 1;
		}
		if(i == n || n - i < k){
			return 0;
		}
		int res = dfs(i + 1, k, start, n, dp);
		if(start == 1){
			res = (res + dfs(i + 1, k, 0, n, dp)) % MOD;
		}else{
			res = (res + dfs(i, k - 1, 1, n, dp)) % MOD;
		}
		return dp[i][k][start] = res;
	}
}
