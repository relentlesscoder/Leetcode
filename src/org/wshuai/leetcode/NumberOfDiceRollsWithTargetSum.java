package org.wshuai.leetcode;

/**
 * Created by Wei on 10/16/2019.
 * #1155 https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
 */
public class NumberOfDiceRollsWithTargetSum {

	private static final int MOD = 1_000_000_007;

	// time O(f^d)
	public int numRollsToTarget(int d, int f, int target) {
		Integer[][] dp = new Integer[d][target + 1];
		return dfs(0, d, f, target, dp);
	}

	private int dfs(int index, int d, int f, int cur, Integer[][] dp){
		if(cur < 0){
			return 0;
		}
		if(index == d){
			return cur == 0 ? 1 : 0;
		}
		if(dp[index][cur] == null){
			int res = 0;
			for(int i = 1; i <= f; i++){
				res = (res + dfs(index + 1, d, f, cur - i, dp)) % MOD;
			}
			dp[index][cur] = res;
		}
		return dp[index][cur];
	}

	// time O(d*f*target), space O(d*target)
	public int numRollsToTargetDP(int d, int f, int target) {
		int[][] dp = new int[d + 1][target + 1];
		dp[0][0] = 1;
		for(int i = 1; i <= d; i++){
			for(int j = 1; j <= target; j++){
				for(int k = 1; k <= f && k <= j; k++){
					dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;
				}
			}
		}
		return dp[d][target];
	}
}
