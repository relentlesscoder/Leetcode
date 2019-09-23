package org.wshuai.leetcode;

/**
 * Created by Wei on 8/12/2016.
 * #7 https://leetcode.com/problems/reverse-integer/
 */
public class ReverseInteger {
	public static int reverse(int x) {
		if (x == 0 || x == Integer.MIN_VALUE) {
			return 0;
		}
		StringBuilder r = new StringBuilder();
		if (x < 0) {
			r.append("-");
			x = x * -1;
		}
		String v = Integer.toString(x);
		int len = v.length();
		int index = len - 1;
		while (v.charAt(index) == '0') {
			index--;
		}
		for (int i = index; i >= 0; i--) {
			r.append(v.charAt(i));
		}
		long result = Long.parseLong(r.toString());
		if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
			return 0;
		} else {
			return (int) result;
		}
	}
}
