package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2023.
 * #1806 https://leetcode.com/problems/minimum-number-of-operations-to-reinitialize-a-permutation/
 */
public class MinimumNumberOfOperationsToReinitializeAPermutation {

	// time O(n), space O(1)
	public int reinitializePermutation(int n) {
		int res = 0, x = 1;
		do {
			if (x % 2 == 0) {
				x /= 2;
			} else {
				x = n / 2 + (x - 1) / 2;
			}
			res++;
		} while (x != 1);
		return res;
	}
}
