package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/04/2023.
 * #2551 https://leetcode.com/problems/put-marbles-in-bags/
 */
public class PutMarblesInBags {

	// time O(n * log(n)), space O(n)
	public long putMarbles(int[] weights, int k) {
		long res = 0;
		int n = weights.length;
		long[] pairSum = new long[n - 1];
		for (int i = 1; i < n; i++) {
			pairSum[i - 1] = 1L * weights[i] + weights[i - 1];
		}
		Arrays.sort(pairSum);
		for (int i = 0; i < k - 1; i++) {
			res += pairSum[n - 2 - i] - pairSum[i];
		}
		return res;
	}
}
