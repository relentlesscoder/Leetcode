package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Wei on 9/17/19.
 * #1029 https://leetcode.com/problems/two-city-scheduling/
 */
public class TwoCityScheduling {
	// see https://leetcode.com/problems/two-city-scheduling/solution/
	public int twoCitySchedCost(int[][] costs) {
		// Sort by a gain in ascending which company has
		// by sending a person to city A and not to city B
		Arrays.sort(costs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o1[1] - (o2[0] - o2[1]);
			}
		});

		int total = 0;
		int n = costs.length / 2;
		for (int i = 0; i < n; i++) {
			total += costs[i][0] + costs[i + n][1];
		}
		return total;
	}
}
