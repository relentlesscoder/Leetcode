package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/29/16.
 * #394 https://leetcode.com/problems/decode-string/
 */
public class DecodeString {
	public String decodeString(String s) {
		if (s == null || s.isEmpty()) {
			return "";
		}
		int len = s.length();
		int i = 0;
		int j = 0;
		Stack<Integer> counts = new Stack<Integer>();
		Stack<String> vals = new Stack<String>();
		counts.push(1);
		vals.push("");
		while (i < len) {
			if (Character.isDigit(s.charAt(i))) {
				j = i;
				while (j < len && Character.isDigit(s.charAt(j))) {
					j++;
				}
				int count = Integer.parseInt(s.substring(i, j));
				j++;
				i = j;
				while (j < len && Character.isAlphabetic(s.charAt(j))) {
					j++;
				}
				counts.push(count);
				vals.push(s.substring(i, j));
				i = j;
			} else if (s.charAt(i) == ']') {
				int c = counts.pop();
				String v = vals.pop();
				String temp = "";
				while (c > 0) {
					temp += v;
					c--;
				}
				String top = vals.pop();
				vals.push(top + temp);
				i++;
			} else {
				String top = vals.pop();
				vals.push(top + s.charAt(i));
				i++;
			}
		}

		return vals.peek();
	}
}
