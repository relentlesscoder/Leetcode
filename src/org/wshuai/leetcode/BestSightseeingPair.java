package org.wshuai.leetcode;

/**
 * Created by Wei on 10/30/2019.
 * #1014 https://leetcode.com/problems/best-sightseeing-pair/
 */
public class BestSightseeingPair {

	// time O(n), space O(1)
	public int maxScoreSightseeingPair(int[] values) {
		int res = Integer.MIN_VALUE, n = values.length, max = values[0];
		for (int i = 1; i < n; i++) {
			res = Math.max(res, values[i] - i + max);
			max = Math.max(max, values[i] + i);
		}
		return res;
	}
}
