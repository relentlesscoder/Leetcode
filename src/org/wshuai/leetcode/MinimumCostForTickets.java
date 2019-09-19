package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 9/19/19.
 * #983 https://leetcode.com/problems/minimum-cost-for-tickets/
 */
public class MinimumCostForTickets {
	public int mincostTickets(int[] days, int[] costs) {
		Set<Integer> set = new HashSet<>();
		for(int d: days){
			set.add(d);
		}
		int[] dp = new int[366];
		for(int i = 1; i < 366; i++){
			if(!set.contains(i)){
				dp[i] = dp[i-1];
			}else{
				int min = dp[i-1] + costs[0];
				min = Math.min(min, dp[Math.max(0, i-7)] + costs[1]);
				min = Math.min(min, dp[Math.max(0, i-30)] + costs[2]);
				dp[i] = min;
			}
		}
		return dp[365];
	}
}
