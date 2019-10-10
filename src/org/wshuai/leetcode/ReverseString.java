package org.wshuai.leetcode;

/**
 * Created by Wei on 9/18/2016.
 * #344 https://leetcode.com/problems/reverse-string/
 */
public class ReverseString {
	public static String reverseString(String s) {
		if (s == null || s.isEmpty()) {
			return s;
		}
		char[] chars = s.toCharArray();
		int len = chars.length;
		StringBuilder sb = new StringBuilder();
		for (int i = len - 1; i >= 0; i--) {
			sb.append(chars[i]);
		}
		return sb.toString();
	}
}
