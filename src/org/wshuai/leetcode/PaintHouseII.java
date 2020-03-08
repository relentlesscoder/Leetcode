package org.wshuai.leetcode;

/**
 * Created by Wei on 11/04/2016.
 * #0265 https://leetcode.com/problems/paint-house-ii/
 */
public class PaintHouseII {
	// time O(n*k), space O(1)
	public int minCostII(int[][] costs) {
		if(costs == null || costs.length == 0 || costs[0].length == 0){
			return 0;
		}
		int r = costs.length, c = costs[0].length;
		int[] min = findMins(costs[0]);
		for(int i = 1; i < r; i++){
			for(int j = 0; j < c; j++){
				costs[i][j] += min[0] == j ? costs[i - 1][min[1]] : costs[i - 1][min[0]];
			}
			min = findMins(costs[i]);
		}
		return costs[r - 1][min[0]];
	}

	private int[] findMins(int[] cost){
		int[] res = new int[2];
		if(cost.length == 1){
			res[0] = 0;
			return res;
		}
		res[0] = cost[0] < cost[1] ? 0 : 1;
		res[1] = 1 - res[0];
		for(int i = 2; i < cost.length; i++){
			if(cost[i] < cost[res[0]]){
				res[1] = res[0];
				res[0] = i;
			}else if(cost[i] < cost[res[1]]){
				res[1] = i;
			}
		}
		return res;
	}
}
