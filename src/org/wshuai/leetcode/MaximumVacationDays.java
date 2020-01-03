package org.wshuai.leetcode;

/**
 * Created by Wei on 11/22/19.
 * #514 https://leetcode.com/problems/freedom-trail/
 */
public class MaximumVacationDays {
	private int[][] dp;

	public int maxVacationDays(int[][] flights, int[][] days) {
		dp = new int[flights.length][days[0].length];
		return dfs(0, 0, flights, days);
	}

	private int dfs(int city, int week, int[][] flights, int[][] days){
		if(week == days[0].length){
			return 0;
		}
		if(dp[city][week] > 0){
			return dp[city][week];
		}
		int res = days[city][week] + dfs(city, week + 1, flights, days);
		for(int i = 0; i < flights[0].length; i++){
			if(flights[city][i] == 1){
				res = Math.max(res, days[i][week] + dfs(i, week + 1, flights, days));
			}
		}
		dp[city][week] = res;
		return res;
	}
}
