package org.wshuai.leetcode;

/**
 * Created by Wei on 10/12/2016.
 * #0050 https://leetcode.com/problems/powx-n/
 */
public class PowXN {

	// time log(n)
	public double myPow(double x, int n) {
		if (x == -1) {
			return n % 2 == 0 ? 1 : -1;
		}
		if(x == 1){
			return 1;
		}
		if (n == Integer.MIN_VALUE){
			return 0;
		}
		double res = dfs(x, Math.abs(n));
		return n < 0 ? 1 / res : res;
	}

	private double dfs(double x, int n){
		if(n == 1){
			return x;
		}
		if(n == 0){
			return 1;
		}
		double y = dfs(x, n/2);
		double prod = y * y;
		return n % 2 == 0 ? prod : prod * x;
	}
}
