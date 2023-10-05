package org.wshuai.leetcode;

import java.util.ArrayList;

/**
 * Created by Wei on 10/04/2023.
 * #2008 https://leetcode.com/problems/maximum-earnings-from-taxi/
 */
public class MaximumEarningsFromTaxi {

	// time O(n), space O(n)
	public long maxTaxiEarnings(int n, int[][] rides) {
		ArrayList<int[]>[] ridesAt = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			ridesAt[i] = new ArrayList<>();
		}
		for (int[] r : rides) {
			ridesAt[r[0]].add(new int[]{r[1], r[1] - r[0] + r[2]});
		}
		long[] dp = new long[n + 1];
		for (int i = n - 1; i >= 1; i--) {
			for (int[] r : ridesAt[i]) {
				dp[i] = Math.max(dp[i], dp[r[0]] + r[1]);
			}
			dp[i] = Math.max(dp[i], dp[i + 1]);
		}
		return dp[1];
	}
}
