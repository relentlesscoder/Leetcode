package org.wshuai.leetcode;

/**
 * Created by Wei on 10/01/2016.
 * #0256 https://leetcode.com/problems/paint-house/
 */
public class PaintHouse {
	// time O(r*3), space O(1)
	public int minCost(int[][] costs) {
		int r = costs.length;
		if(r == 0){
			return 0;
		}
		for(int i = 1; i < r; i++){
			costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
			costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
			costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
		}
		return Math.min(costs[r - 1][0], Math.min(costs[r - 1][1], costs[r - 1][2]));
	}
}
