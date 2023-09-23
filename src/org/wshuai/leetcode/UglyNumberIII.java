package org.wshuai.leetcode;

/**
 * Created by Wei on 11/13/2019.
 * #1201 https://leetcode.com/problems/ugly-number-iii/
 */
public class UglyNumberIII {

	// time O(log(high)), space O(1)
	// https://leetcode.com/problems/ugly-number-iii/discuss/387539/cpp-Binary-Search-with-picture-and-Binary-Search-Template
	public int nthUglyNumber(int n, int a, int b, int c) {
		long low = 1, high = (long) 2e9, ab = lcm(a, b), ac = lcm(a, c), bc = lcm(b, c), abc = lcm(a, bc);
		while (low < high) {
			long mid = (low + high) >> 1,
					count = mid / a + mid / b + mid / c - mid / ab - mid / bc - mid / ac + mid / abc;
			if (count >= n) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return (int) low;
	}

	private long lcm(long a, long b) {
		return (a * b) / gcd(a, b);
	}

	private long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
}
