package org.wshuai.leetcode;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/13/19.
 * #1058 https://leetcode.com/problems/minimize-rounding-error-to-meet-target/
 */
public class MinimizeRoundingErrorToMeetTarget {

	// Knapsack problem, TLE due to large target otherwise better than the DFS with memorization solution below
	public String minimizeError(String[] prices, int target) {
		int N = prices.length;
		Double[][] dp = new Double[N + 1][target + 1];
		dp[0][0] = 0.0;
		for(int i = 1; i <= N; i++){
			for(int j = 0; j <= target; j++){
				double d = Double.parseDouble(prices[i - 1]);
				double floor = Math.floor(d);
				double ceil = Math.ceil(d);
				int fl = (int)floor;
				int ce = (int)ceil;
				dp[i][j] = 2_000_000.0;
				if(j >= fl && dp[i - 1][j - fl] != null){
					dp[i][j] = Math.min(dp[i][j], d - floor + dp[i - 1][j - fl]);
				}
				if(j >= ce && dp[i - 1][j - ce] != null){

					dp[i][j] = Math.min(dp[i][j], ceil - d + dp[i - 1][j - ce]);
				}
				if(dp[i][j] == 2_000_000.0){
					dp[i][j] = null;
				}
			}
		}
		DecimalFormat df = new DecimalFormat("0.000");
		return dp[N][target] > 1_000_000 ? "-1" : df.format(dp[N][target]);
	}

	private Map<String, Double> map = new HashMap<>();

	public String minimizeErrorDFSWithMemo(String[] prices, int target) {
		double min = dfs(0, prices, target);
		DecimalFormat df = new DecimalFormat("0.000");
		return min > 1_000_000 ? "-1" : df.format(min);
	}

	private double dfs(int i, String[] p, int t){
		if(t < 0){
			return 5_000_000;
		}
		if(i == p.length && t == 0){
			return 0.0;
		}
		if(i == p.length){
			return 5_000_000;
		}
		String key = i + "," + t;
		if(map.containsKey(key)){
			return map.get(key);
		}
		double d = Double.parseDouble(p[i]);
		double floor = Math.floor(d);
		double ceil = Math.ceil(d);
		map.put(key, Math.min(d - floor + dfs(i + 1, p, t - (int)floor)
				, ceil - d + dfs(i + 1, p, t - (int)ceil)));
		return map.get(key);
	}
}
