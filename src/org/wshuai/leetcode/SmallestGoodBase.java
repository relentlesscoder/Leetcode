package org.wshuai.leetcode;

import java.math.BigInteger;

/**
 * Created by Wei on 12/31/2019.
 * #483 https://leetcode.com/problems/smallest-good-base/
 */
public class SmallestGoodBase {
	// http://mathworld.wolfram.com/GeometricSeries.html
	// https://leetcode.com/problems/smallest-good-base/discuss/96602/JavaC-binary-search-solutions-with-detailed-explanation
	public String smallestGoodBase(String n) {
		BigInteger N = new BigInteger(n);
		long base = Long.MAX_VALUE;

		for (int k = 2; k < 64; k++) {

			long l = 2, r = Long.MAX_VALUE;
			while (l <= r) {
				long mid = l + (r - l) / 2;

				BigInteger cb = BigInteger.valueOf(mid).pow(k).subtract(BigInteger.ONE);
				BigInteger wb = N.multiply(BigInteger.valueOf(mid).subtract(BigInteger.ONE));

				int cmp = cb.compareTo(wb);
				if (cmp == 0) {
					base = Math.min(base, mid);
					break;
				} else if (cmp < 0) {
					l = mid + 1;
				} else {
					r = mid - 1;
				}
			}
		}

		return "" + base;
	}
}
