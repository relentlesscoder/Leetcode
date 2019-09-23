package org.wshuai.leetcode;

/**
 * Created by Wei on 11/21/2016.
 * #263 https://leetcode.com/problems/ugly-number/
 */
public class UglyNumber {
	public boolean isUgly(int num) {
		if (num < 1) {
			return false;
		}
		while (num % 2 == 0) {
			num /= 2;
		}
		while (num % 3 == 0) {
			num /= 3;
		}
		while (num % 5 == 0) {
			num /= 5;
		}
		return num == 1;
	}
}
