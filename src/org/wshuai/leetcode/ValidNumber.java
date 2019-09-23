package org.wshuai.leetcode;

/**
 * Created by Wei on 10/16/16.
 * #65 https://leetcode.com/problems/valid-number/
 */
public class ValidNumber {
	public boolean isNumber(String s) {
		if (s == null || s.isEmpty()) {
			return false;
		}

		int len = s.length();
		int i = 0;

		while (i < len && s.charAt(i) == ' ') {
			i++;
		}

		if (i < len && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
			i++;
		}

		boolean isNumber = false;

		while (i < len && Character.isDigit(s.charAt(i))) {
			isNumber = true;
			i++;
		}

		if (i < len && s.charAt(i) == '.') {
			i++;
			while (i < len && Character.isDigit(s.charAt(i))) {
				isNumber = true;
				i++;
			}
		}

		if (isNumber && i < len && (s.charAt(i) == 'E' || s.charAt(i) == 'e')) {
			i++;
			isNumber = false;
			if (i < len && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
				i++;
			}
			while (i < len && Character.isDigit(s.charAt(i))) {
				isNumber = true;
				i++;
			}
		}

		while (i < len && s.charAt(i) == ' ') {
			i++;
		}

		return i == len && isNumber;
	}
}
