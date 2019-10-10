package org.wshuai.leetcode;

/**
 * Created by Wei on 10/1/2016.
 * #256 https://leetcode.com/problems/paint-house/
 */
public class PaintHouse {
	public int minCost(int[][] costs) {
		if (costs == null) {
			return 0;
		}
		int rLen = costs.length;
		if (rLen == 0) {
			return 0;
		}
		int cLen = costs[0].length;
		if (cLen != 3) {
			return 0;
		}

		for (int i = 0; i < rLen; i++) {
			if (i == 0) {
				continue;
			}
			costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
			costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
			costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
		}

		int row = rLen - 1;
		int min = Math.min(costs[row][0], costs[row][1]);
		min = Math.min(min, costs[row][2]);
		return min;
	}
}
