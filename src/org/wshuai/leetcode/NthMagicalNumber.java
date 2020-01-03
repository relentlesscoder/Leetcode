package org.wshuai.leetcode;

/**
 * Created by Wei on 01/03/2020.
 * #878 https://leetcode.com/problems/nth-magical-number/
 */
public class NthMagicalNumber {
	// https://leetcode.com/problems/nth-magical-number/discuss/154613/C%2B%2BJavaPython-Binary-Search
	public int nthMagicalNumber(int N, int A, int B) {
		long a = (long)gcd(A, B), l = 2, r = (long)1e14, mod = (long)1e9 + 7;
		while (l < r) {
			long m = (l + r) / 2;
			if (m / A + m / B - m / (A * B / a) < N) l = m + 1;
			else r = m;
		}
		return (int)(l % mod);
	}

	private int gcd(int x, int y){
		return x == 0 ? y : gcd(y % x, x);
	}
}

/*
		4 points to figure out:

		Get gcd (greatest common divisor) and lcm (least common multiple) of (A, B).
		(a, b) = (A, B) while b > 0: (a, b) = (b, a % b)
		then, gcd = a and lcm = A * B / a

		How many magic numbers <= x ?
		By inclusion exclusion principle, we have:
		x / A + x / B - x / lcm

		Set our binary search range
		Lower bound is min(A, B), I just set left = 2.
		Upper bound is N * min(A, B), I just set right = 10 ^ 14.

		binary search, find the smallest x that x / A + x / B - x / lcm = N

*/
