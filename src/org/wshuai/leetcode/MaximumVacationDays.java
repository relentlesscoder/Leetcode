package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/22/2019.
 * #0568 https://leetcode.com/problems/maximum-vacation-days/
 */
public class MaximumVacationDays {

	// time O(k*n^2), space O(n*k)
	public int maxVacationDays(int[][] flights, int[][] days) {
		int n = flights.length, k = days[0].length;
		int[] dp = new int[n];
		Arrays.fill(dp, Integer.MIN_VALUE);
		dp[0] = 0;
		for(int d = 0; d < k; d++){
			int[] next = new int[n];
			Arrays.fill(next, Integer.MIN_VALUE);
			for(int j = 0; j < n; j++){
				for(int i = 0; i < n; i++){
					if(i == j || flights[i][j] == 1){
						next[j] = Math.max(next[j], dp[i] + days[j][d]);
					}
				}
			}
			dp = next;
		}
		int max = 0;
		for(int i = 0; i < n; i++){
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	// time O(n^k), space O(n*k)
	public int maxVacationDaysMemorization(int[][] flights, int[][] days) {
		int n = flights.length, k = days[0].length;
		int[][] dp = new int[n][k];
		return dfs(0, 0, n, k, flights, days, dp);
	}

	private int dfs(int city, int week, int n, int k, int[][] flights, int[][] days, int[][] dp){
		if(week == k){
			return 0;
		}
		if(dp[city][week] > 0){
			return dp[city][week];
		}
		int res = days[city][week] + dfs(city, week + 1, n, k, flights, days, dp);
		for(int to = 0; to < n; to++){
			if(flights[city][to] == 0){
				continue;
			}
			res = Math.max(res, days[to][week] + dfs(to, week + 1, n, k, flights, days, dp));
		}
		dp[city][week] = res;
		return res;
	}
}
