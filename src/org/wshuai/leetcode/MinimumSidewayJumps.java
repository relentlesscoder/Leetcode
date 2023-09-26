package org.wshuai.leetcode;

/**
 * Created by Wei on 09/26/2023.
 * #1824 https://leetcode.com/problems/minimum-sideway-jumps/
 */
public class MinimumSidewayJumps {

	// time O(n), space O(1)
	public int minSideJumps(int[] obstacles) {
		int n = obstacles.length;
		int[] dp = new int[] {1, 0, 1}; // dp[i] denotes the cost of lane i + 1
		for (int i = 1; i < n; i++) {
			if (obstacles[i] > 0) {
				dp[obstacles[i] - 1] = 1_000_000; // overrides cost of the lane to max if there is an obstacle in lane, meaning we can't proceed without changing the lane
			}
			for (int j = 0; j < 3; j++) {
				if (j + 1 != obstacles[i]) { // special case: obstacles[i] = 0, maintain the current cost
					dp[j] = Math.min(dp[j], Math.min(dp[(j + 1) % 3], dp[(j + 2) % 3]) + 1); // for lanes without obstacle, add the jump cost to the min between the costs from the other two lanes
				}
			}
		}
		return Math.min(dp[0], Math.min(dp[1], dp[2]));
	}
}
