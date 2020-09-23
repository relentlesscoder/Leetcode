package org.wshuai.leetcode;

/**
 * Created by Wei on 01/07/2020.
 * #0029 https://leetcode.com/problems/divide-two-integers/
 */
public class DivideTwoIntegers {

	// time O((log(n))^2)
	public int divide(int dividend, int divisor) {
		if (dividend == Integer.MIN_VALUE && divisor == -1) {
			return Integer.MAX_VALUE;
		}
		long dvd = Math.abs((long)dividend), dvs = Math.abs((long)divisor), ans = 0;
		int sign = (dividend ^ divisor) < 0 ? -1 : 1;
		// this check covers the base case div >= dvs,
		// so we start with m = 1 in the inner loop
		while (dvd >= dvs) {
			long temp = dvs, m = 1;
			while (temp << 1 <= dvd) {
				temp <<= 1;
				m <<= 1;
			}
			dvd -= temp;
			ans += m;
		}
		return sign * (int)ans;
	}
}
