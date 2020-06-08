package org.wshuai.leetcode;

/**
 * Created by Wei on 06/08/2020.
 * #1473 https://leetcode.com/problems/paint-house-iii/
 */
public class PaintHouseIII {

	private static final int MAX = 100_000_000;

	public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
		int[][][] dp = new int[m][n + 1][target + 1];
		for(int i = 0; i < m; i++){
			for(int j = 0; j <= n; j++){
				for(int k = 0; k <= target; k++){
					dp[i][j][k] = -1;
				}
			}
		}
		int res = dfs(0, 0, 0, houses, cost, m, n, target, dp);
		return res >= MAX ? -1 : res;
	}

	private int dfs(int i, int j, int k, int[] houses, int[][] cost, int m, int n, int target, int[][][] dp){
		if(i == m){
			return k == target ? 0 : MAX;
		}
		if(k > target){
			return MAX;
		}
		if(dp[i][j][k] != -1){
			return dp[i][j][k];
		}
		int res = MAX;
		if(houses[i] > 0){
			res = dfs(i + 1, houses[i], k + (houses[i] == j ? 0 : 1), houses, cost, m, n, target, dp);
		}else{
			for(int c = 0; c < n; c++){
				res = Math.min(res, cost[i][c] + dfs(i + 1, c + 1, k + (c + 1 == j ? 0 : 1), houses, cost, m, n, target, dp));
			}
		}
		dp[i][j][k] = res;
		return res;
	}
}
