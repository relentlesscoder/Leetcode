package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2023.
 * #2145 https://leetcode.com/problems/count-the-hidden-sequences/
 */
public class CountTheHiddenSequences {

	// time O(n), space O(n)
	public int numberOfArrays(int[] differences, int lower, int upper) {
		int n = differences.length, range = 0;
		long min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, prefixSum = 0;
		for (int i = 1; i <= n; i++) {
			prefixSum = differences[i - 1] + prefixSum;
			max = Math.max(max, prefixSum);
			min = Math.min(min, prefixSum);
		}
		range = (int) (Math.max(0, max) - Math.min(0, min)); // edge case when there is only one difference like [-40]
		return Math.max(upper - lower - range + 1, 0);
	}
}
