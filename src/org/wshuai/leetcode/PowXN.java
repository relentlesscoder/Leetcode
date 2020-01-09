package org.wshuai.leetcode;

/**
 * Created by Wei on 10/12/2016.
 * #0050 https://leetcode.com/problems/powx-n/
 */
public class PowXN {
	// time log(n)
	public double myPow(double x, int n) {
		double res = getPow(x, Math.abs(n));
		return n < 0 ? 1 / res : res;
	}

	private double getPow(double x, int n){
		if(n == 0){
			return 1.0;
		}
		if(n == 1){
			return x;
		}
		double pow = getPow(x, n / 2);
		double prod = pow * pow;
		return n % 2 == 0 ? prod : prod * x;
	}
}
