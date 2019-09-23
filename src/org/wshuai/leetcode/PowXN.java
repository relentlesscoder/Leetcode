package org.wshuai.leetcode;

/**
 * Created by Wei on 10/12/2016.
 * #50 https://leetcode.com/problems/powx-n/
 */
public class PowXN {
	public double myPow(double x, int n) {
		double r = myPowUtil(x, Math.abs(n));
		return n < 0 ? 1 / r : r;
	}

	private double myPowUtil(double x, int n) {
		if (n == 0) {
			return 1.0;
		}
		if (n == 1) {
			return x;
		}
		double t = myPowUtil(x, n / 2);
		if (n % 2 == 0) {
			return t * t;
		} else {
			return t * t * x;
		}
	}
}
