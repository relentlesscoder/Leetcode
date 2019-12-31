package org.wshuai.leetcode;

/**
 * Created by Wei on 12/30/2019.
 * #793 https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function/
 */
public class PreimageSizeOfFactorialZeroesFunction {
	// https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function/discuss/117821/Four-binary-search-solutions-based-on-different-ideas
	public int preimageSizeFZF(int K) {
		for (long l = 0, r =  5L * (K + 1); l <= r;) {
			long m = l + (r - l) / 2;
			long k = numOfTrailingZeros(m);

			if (k < K) {
				l = m + 1;
			} else if (k > K) {
				r = m - 1;
			} else {
				return 5;
			}
		}

		return 0;
	}

	private long numOfTrailingZeros(long x) {
		long res = 0;

		for (; x > 0; x /= 5) {
			res += x/5;
		}

		return res;
	}
}
