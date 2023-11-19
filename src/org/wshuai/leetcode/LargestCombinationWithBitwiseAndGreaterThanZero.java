package org.wshuai.leetcode;

/**
 * Created by Wei on 11/03/2023.
 * #2275 https://leetcode.com/problems/largest-combination-with-bitwise-and-greater-than-zero/
 */
public class LargestCombinationWithBitwiseAndGreaterThanZero {

	// time O(24 * n), space O(1)
	public int largestCombination(int[] candidates) {
		int res = 0, n = candidates.length;
		for (int i = 0; i < 24; i++) {// since the max possible value is 10^7 = 100110001001011010000000 (24 bits)
			int countOneBit = 0;
			for (int j = 0; j < n; j++) {
				countOneBit += ((candidates[j] >> i) & 1); // for each bit position, find the count of 1 bit
			}
			res = Math.max(res, countOneBit); // largest AND combination is the bit position with most 1 bit
		}
		return res;
	}
}
