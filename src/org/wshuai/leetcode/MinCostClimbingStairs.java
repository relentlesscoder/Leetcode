package org.wshuai.leetcode;

/**
 * Created by Wei on 08/21/2019.
 * #0746 https://leetcode.com/problems/min-cost-climbing-stairs/
 */
public class MinCostClimbingStairs {
	// time O(n), space O(1)
	public int minCostClimbingStairs(int[] cost) {
		int s1 = cost[0], s2 = cost[1];
		for(int i = 2; i < cost.length; i++){
			int cur = Math.min(s1, s2) + cost[i];
			s1 = s2;
			s2 = cur;
		}
		// can reach from both n - 1 and n - 2
		// so we take the one smaller
		return Math.min(s1, s2);
	}
}
