package org.wshuai.leetcode;

/**
 * Created by Wei on 8/13/2016.
 */
public class StringToInteger {
	public static int myAtoi(String str) {
		if (str == null) {
			throw new IllegalArgumentException("Invalid input");
		}
		if (str.isEmpty()) {
			return 0;
		}

		int len = str.length();
		int index = 0;
		while (str.charAt(index) == ' ') {
			index++;
		}
		if (index >= len) {
			return 0;
		}
		long temp = 0;
		boolean negative = false;
		for (int i = index; i < len; i++) {
			char val = str.charAt(i);
			if (i == index && val == '-') {
				negative = true;
				continue;
			} else if (i == index && val == '+') {
				continue;
			}
			if (val <= '9' && val >= '0') {
				int num = (int) val - 48;
				if (negative) {
					temp = temp * 10 + num * (-1);
					if (temp < Integer.MIN_VALUE) {
						return Integer.MIN_VALUE;
					}
				} else {
					temp = temp * 10 + num;
					if (temp > Integer.MAX_VALUE) {
						return Integer.MAX_VALUE;
					}
				}
			} else {
				break;
			}
		}

		return (int) temp;
	}
}
