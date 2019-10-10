package org.wshuai.leetcode;

/**
 * Created by Wei on 4/4/2017.
 * #507 https://leetcode.com/problems/perfect-number/
 */
public class PerfectNumber {
	public boolean checkPerfectNumber(int num) {
		if (num <= 1) {
			return false;
		}
		//1 is always valid
		int sum = 1;
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				sum += i;
				int rem = num / i;
				sum += rem != i ? rem : 0;
			}
		}
		return sum == num;
	}
}
