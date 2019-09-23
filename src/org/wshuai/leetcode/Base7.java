package org.wshuai.leetcode;

/**
 * Created by Wei on 2/21/17.
 * #504 https://leetcode.com/problems/base-7/
 */
public class Base7 {
	public String convertToBase7(int num) {
		if (num == 0) {
			return "0";
		}
		StringBuilder res = new StringBuilder();
		boolean neg = false;
		if (num < 0) {
			num = Math.abs(num);
			neg = true;
		}
		while (num != 0) {
			res.append(num % 7);
			num /= 7;
		}
		if (neg) {
			res.append("-");
		}
		return res.reverse().toString();
	}
}
